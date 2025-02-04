package com.cgigueira.universalpetcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.exception.UserAlreadyExistsException;
import com.cgigueira.universalpetcare.exception.UserNotFoundException;
import com.cgigueira.universalpetcare.request.UserUpdateRequest;
import com.cgigueira.universalpetcare.service.user.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<Response> registerUser(@RequestBody UserDto registrationRequest) {
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(
          this.userService.registerUser(registrationRequest));
    } catch (UserAlreadyExistsException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(
          Response.builder()
              .status(409)
              .message(e.getMessage())
              .build());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
          Response.builder()
              .status(500)
              .message(e.getMessage())
              .build());
    }
  }
  
  @PutMapping("/update/user/{userId}")
  public ResponseEntity<Response> updateUser(
    @PathVariable Long userId,
    @RequestBody UserUpdateRequest request
  ) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(this.userService.updateUser(userId, request));
    } catch (UserNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.builder()
          .status(404)
          .message(e.getMessage())
          .build());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
          Response.builder()
              .status(500)
              .message(e.getMessage())
              .build());
    }
  }
  
  @GetMapping("/get/user/{userId}")
  public ResponseEntity<Response> getUserById(@PathVariable Long userId) {
    try {
      return ResponseEntity.status(HttpStatus.FOUND).body(this.userService.getUserById(userId));
    } catch (UserNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
          Response.builder()
              .status(404)
              .message(e.getMessage())
              .build());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
          Response.builder()
              .status(500)
              .message(e.getMessage())
              .build());
    }
  }
  
  @DeleteMapping("/delete/user/{userId}")
  public ResponseEntity<Response> deleteUser(@PathVariable Long userId) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(this.userService.deleteUser(userId));
    } catch (UserNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
          Response.builder()
              .status(404)
              .message(e.getMessage())
              .build());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
          Response.builder()
              .status(500)
              .message(e.getMessage())
              .build());
    }
  }

  @GetMapping("/get/all")
  public ResponseEntity<Response> getAllUsers() {
    return ResponseEntity.status(HttpStatus.FOUND).body(this.userService.getAllUsers());
  }

}
