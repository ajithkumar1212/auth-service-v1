package com.auth_service_v1.service.user;

import com.auth_service_v1.dto.user.UserDto;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

  public Optional<UserDto> findByMobileNumber(String mobileNumber);

  public UserDetails toUserDetails(UserDto userDto);

  public Optional<UserDto> findOrCreateUser(String mobileNumber);
}
