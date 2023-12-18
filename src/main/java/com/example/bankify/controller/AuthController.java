/**
 * Class: AuthController
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.controller;

import com.example.bankify.dto.LoginDto;
import com.example.bankify.service.AuthService;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
    String token = authService.authenticate(loginDto);
    return ResponseEntity.ok(Collections.singletonMap("token", token));
  }
}