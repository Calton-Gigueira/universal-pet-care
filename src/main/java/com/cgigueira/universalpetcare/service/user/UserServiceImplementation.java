package com.cgigueira.universalpetcare.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.exception.UserNotFoundException;
import com.cgigueira.universalpetcare.factory.UserFactory;
import com.cgigueira.universalpetcare.mapper.EntityDtoMapper;
import com.cgigueira.universalpetcare.model.User;
import com.cgigueira.universalpetcare.repository.UserRepository;
import com.cgigueira.universalpetcare.request.UserUpdateRequest;

@Service
public class UserServiceImplementation implements UserService {

  @Autowired
  private UserFactory userFactory;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EntityDtoMapper entityDtoMapper;

  @Override
  public Response registerUser(UserDto registrationRequest) {
    return this.userFactory.createUser(registrationRequest);
  }

  @Override
  public Response updateUser(Long userId, UserUpdateRequest request) {
    UserDto userDto = this.getUserById(userId).getUserDto();
    User user = this.entityDtoMapper.mapDtoToUserBasic(userDto);

    if (request.getFirstName() != null)
      user.setFirstName(request.getFirstName());
    if (request.getLastName() != null)
      user.setLastName(request.getLastName());
    if (request.getGender() != null)
      user.setGender(request.getGender());
    if (request.getPhoneNumber() != null)
      user.setPhoneNumber(request.getPhoneNumber());
    if (request.getSpecialization() != null)
      user.setSpecialization(request.getSpecialization());

    User savedUser = this.userRepository.save(user);
    userDto = this.entityDtoMapper.mapUserToDtoBasic(savedUser);
      
    return Response.builder()
      .status(200)
      .message("User updated successfulyy")
      .userDto(userDto)
      .build();
  }

  @Override
  public Response getUserById(Long userId) {
    User user = this.userRepository.findById(userId).
      orElseThrow(() -> new UserNotFoundException("User not found"));
    UserDto userDto = this.entityDtoMapper.mapUserToDtoBasic(user);

    return Response.builder()
      .status(302)
      .message("User found")
      .userDto(userDto)
      .build();
  }

  @Override
  public Response deleteUser(Long userId) {
    this.getUserById(userId);

    this.userRepository.deleteById(userId);

    return Response.builder()
      .status(200)
      .message("User deleted successfuly")
      .build();
  }

  @Override
  public Response getAllUsers() {
    List<User> users = this.userRepository.findAll();
    List<UserDto> userDtos = users.stream()
      .map(this.entityDtoMapper::mapUserToDtoBasic)
      .toList();
    return Response.builder()
      .status(302)
      .message("All users")
      .userDtos(userDtos)
      .build();
  }

}
