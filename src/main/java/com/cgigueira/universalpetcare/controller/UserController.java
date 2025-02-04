package com.cgigueira.universalpetcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.exception.UserAlreadyExistsException;
import com.cgigueira.universalpetcare.service.user.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<Response> registerUser(@RequestBody UserDto registrationRequest) {
    try {
      return ResponseEntity.ok(this.userService.registerUser(registrationRequest));
    } catch (UserAlreadyExistsException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(
        Response.builder()
          .status(409)
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
