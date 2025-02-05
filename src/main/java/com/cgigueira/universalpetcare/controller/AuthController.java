package com.cgigueira.universalpetcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.exception.InvalidCredentialsException;
import com.cgigueira.universalpetcare.exception.UserAlreadyExistsException;
import com.cgigueira.universalpetcare.request.LoginRequest;
import com.cgigueira.universalpetcare.service.user.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

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

  @PostMapping("/login")
  public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
    try {
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userService.loginUser(loginRequest));
    } catch (UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        Response.builder()
          .status(404)
          .message(e.getMessage())
          .build()
      );
    } catch (InvalidCredentialsException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
        Response.builder()
          .status(401)
          .message(e.getMessage())
          .build()
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
        Response.builder()
          .status(500)
          .message(e.getMessage())
          .build()
      );
    }
  }
}
