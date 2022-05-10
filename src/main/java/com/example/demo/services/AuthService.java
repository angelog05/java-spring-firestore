package com.example.demo.services;

import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.response.JwtResponse;

public interface AuthService {
  
  /** 
   * Signin 
   * 
   * @param loginRequest
   */
  JwtResponse generateTokenByUser(LoginRequest loginRequest);
}
