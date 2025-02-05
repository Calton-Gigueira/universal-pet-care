package com.cgigueira.universalpetcare.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgigueira.universalpetcare.dto.AdminDto;
import com.cgigueira.universalpetcare.dto.Response;
import com.cgigueira.universalpetcare.dto.UserDto;
import com.cgigueira.universalpetcare.enums.UserRole;
import com.cgigueira.universalpetcare.mapper.EntityDtoMapper;
import com.cgigueira.universalpetcare.model.Admin;
import com.cgigueira.universalpetcare.repository.AdminRepository;

@Service
public class AdminFactory {

  @Autowired
  private EntityDtoMapper entityDtoMapper;

  @Autowired
  private AdminRepository adminRepository;

  public Response createAdmin(UserDto registrationRequest) {
    Admin admin = new Admin();
    this.entityDtoMapper.setCommonAttributes(registrationRequest, admin);
    admin.setRole(UserRole.ADMIN);
    Admin saveAdmin = this.adminRepository.save(admin);

    AdminDto adminDto = this.entityDtoMapper.mapAdminToDtoBasic(saveAdmin);
    return Response.builder()
      .status(201)
      .message("Admin Registered Successfully")
      .adminDto(adminDto)
      .build();
  }

}
