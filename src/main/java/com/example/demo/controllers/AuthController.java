package com.example.demo.controllers;

import javax.validation.Valid;

import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private AuthService service;

  @Autowired
  AuthController(AuthService service) {
    this.service = service;
  }

  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> signin(@Valid @RequestBody LoginRequest loginRequest) {
    JwtResponse result = this.service.generateTokenByUser(loginRequest);

    return ResponseEntity.ok(result);
  }

  @PostMapping("/signup")
  public ResponseEntity<MessageResponse> signup(@Valid @RequestBody SignupRequest signUpRequest) {
    MessageResponse result = this.service.registerUser(signUpRequest);

    return ResponseEntity.ok(result);
  }
}
