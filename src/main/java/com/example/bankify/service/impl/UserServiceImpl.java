/**
 * Class: UserServiceImpl
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.service.impl;

import com.example.bankify.dto.UserDto;
import com.example.bankify.dto.UserProfileDto;
import com.example.bankify.model.User;
import com.example.bankify.repository.UserRepository;
import com.example.bankify.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDto registerUser(UserDto userDto) {
    User newUser = new User();
    newUser.setUsername(userDto.getEmail());
    newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
    newUser.setFullName(userDto.getFullName());
    newUser.setAddress(userDto.getAddress());
    newUser.setPhoneNumber(userDto.getPhoneNumber());
    User savedUser = userRepository.save(newUser);

    return mapToUserDto(savedUser);
  }

  @Override
  public UserProfileDto getUserProfile(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    return mapToUserProfileDto(user);
  }

  private UserDto mapToUserDto(User user) {
    return new UserDto(user.getUsername(),user.getPassword(), user.getFullName(), user.getAddress(), user.getPhoneNumber());
  }

  private UserProfileDto mapToUserProfileDto(User user) {
    return new UserProfileDto(user.getId(), user.getUsername(), user.getFullName(), user.getAddress(), user.getPhoneNumber());
  }
}
