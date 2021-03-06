package com.example.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.enumerators.ERole;
import com.example.demo.exeptions.BadRequestException;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.jwt.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  RoleRepository roleRepository;
  
  @Autowired
  PasswordEncoder encoder;

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtUtils jwtUtils;

  @Override
  public JwtResponse generateTokenByUser(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return new JwtResponse(
      jwt, 
      userDetails.getId(), 
      userDetails.getUsername(), 
      userDetails.getEmail(), 
      roles
    );
  }

  @Override
  public MessageResponse registerUser(SignupRequest signUpRequest) {

    if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
      return new MessageResponse("Error: Username is already taken!");
    }

    if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
      return new MessageResponse("Error: Email is already in use!");
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
          .orElseThrow(() -> new BadRequestException());
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
          .orElseThrow(() -> new BadRequestException());
        roles.add(userRole);
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return new MessageResponse("User registered successfully!");
  }  
}
