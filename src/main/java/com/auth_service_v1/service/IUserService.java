package com.auth_service_v1.service;

import com.auth_service_v1.dto.UserDto;

public interface IUserService {

  public UserDto findByPhoneNumber(String phoneNumber);

  public UserDto createOrVerifyUser(String phoneNumber);
}
