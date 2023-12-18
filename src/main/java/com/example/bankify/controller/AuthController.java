/**
 * Class: AuthController
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.controller;

import com.example.bankify.dto.LoginDto;
import com.example.bankify.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/auth")
public class AuthController {

  AuthenticationManager authenticationManager;

  JwtTokenProvider jwtTokenProvider;

  @PostMapping("/login")
  public String login(@RequestBody LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    return jwtTokenProvider.createToken(userDetails.getUsername(), userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .toList());
  }
}