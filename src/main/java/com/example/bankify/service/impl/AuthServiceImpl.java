/**
 * Class: AuthServiceImpl
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.service.impl;

import com.example.bankify.api.errors.ErrorCode;
import com.example.bankify.api.exceptions.CustomException;
import com.example.bankify.dto.LoginDto;
import com.example.bankify.security.JwtTokenProvider;
import com.example.bankify.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;

  @Override
  public String authenticate(LoginDto loginDto) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      return tokenProvider.createToken(userDetails.getUsername());
    } catch (AuthenticationException e) {
      throw new CustomException(ErrorCode.AUTHORIZATION_FAILED);
    }
  }
}
