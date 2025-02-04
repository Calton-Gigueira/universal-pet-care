package com.cgigueira.universalpetcare.service.user;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.request.UserUpdateRequest;

public interface UserService {

  public Response registerUser(UserDto registrationRequest);

  public Response updateUser(Long userId, UserUpdateRequest request);

  public Response getUserById(Long userId);

  public Response deleteUser(Long userId);

  public Response getAllUsers();

}
