package com.cgigueira.universalpetcare.factory;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;

public interface UserFactory {

  public Response createUser(UserDto registrationRequest);

}
