package com.auth_service_v1.service.impl;

import com.auth_service_v1.dto.UserDto;
import com.auth_service_v1.entity.Permission;
import com.auth_service_v1.entity.Role;
import com.auth_service_v1.entity.User;
import com.auth_service_v1.repository.PermissionRepository;
import com.auth_service_v1.repository.RoleRepository;
import com.auth_service_v1.repository.UserRepository;
import com.auth_service_v1.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Autowired private UserRepository userRepository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private PermissionRepository permissionRepository;

  @Autowired private ModelMapper modelMapper;

  @Override
  public UserDto createOrVerifyUser(String phoneNumber) {
    User user = userRepository.findByPhoneNumber(phoneNumber);
    if (user == null) {
      user = new User(phoneNumber);
      Role userRole = roleRepository.findByName("USER").orElse(null);
      if (userRole == null) {
        userRole = new Role("USER");
        Permission readPerm = new Permission("READ");
        permissionRepository.save(readPerm);
        userRole.getPermissions().add(readPerm);
        roleRepository.save(userRole);
      }
      user.getRoles().add(userRole);
      user = userRepository.save(user);
    }
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public UserDto findByPhoneNumber(String phoneNumber) {
    User user = userRepository.findByPhoneNumber(phoneNumber);
    return modelMapper.map(user, UserDto.class);
  }
}
