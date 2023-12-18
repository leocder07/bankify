/**
 * Interface: AuthService
 * <p>
 * Description: Defines the contract for...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.service;

import com.example.bankify.dto.LoginDto;

public interface AuthService {
  String authenticate(LoginDto loginDto);
}