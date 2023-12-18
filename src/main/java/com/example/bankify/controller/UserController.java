/**
 * Class: userController
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.controller;

import com.example.bankify.dto.UserDto;
import com.example.bankify.dto.UserProfileDto;
import com.example.bankify.service.UserService;
import com.example.bankify.utils.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
    UserDto registeredUser = userService.registerUser(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
  }

  @GetMapping("/profile")
  public ResponseEntity<UserProfileDto> getUserProfile() {
    String username = SecurityUtils.getCurrentUsername();
    UserProfileDto profile = userService.getUserProfile(username);
    return ResponseEntity.ok(profile);
  }
}