/**
 * Interface: UserService
 * <p>
 * Description: Defines the contract for...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.service;

import com.example.bankify.dto.UserDto;
import com.example.bankify.dto.UserProfileDto;
import com.example.bankify.model.User;

public interface UserService {
  UserDto registerUser(UserDto userDto);
  UserProfileDto getUserProfile(String username);
}
