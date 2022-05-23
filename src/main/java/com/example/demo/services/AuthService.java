package com.example.demo.services;

import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;

public interface AuthService {
  
  /** 
   * Authenticate user and generate token
   * 
   * @param loginRequest object with user data
   */
  JwtResponse generateTokenByUser(LoginRequest loginRequest);

  /** 
   * Authenticate user and generate token
   * 
   * @param signUpRequest object with user data
   */
  MessageResponse registerUser(SignupRequest signUpRequest);
}
