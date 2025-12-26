package com.auth_service_v1.service.impl.user;

import com.auth_service_v1.dto.user.UserDto;
import com.auth_service_v1.entity.user.Role;
import com.auth_service_v1.entity.user.User;
import com.auth_service_v1.repository.user.RoleRepository;
import com.auth_service_v1.repository.user.UserRepository;
import com.auth_service_v1.service.user.IUserService;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class InternalAuthUserService implements IUserService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  public InternalAuthUserService(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Autowired private ModelMapper modelMapper;

  @Override
  public Optional<UserDto> findUser(String mobile) {
    User user = userRepository.findByMobileNumber(mobile);
    return Optional.ofNullable(modelMapper.map(user, UserDto.class));
  }

  @Override
  public Optional<UserDto> findOrCreateUser(String mobile) {
    User user = userRepository.findByMobileNumber(mobile);
    if (Optional.ofNullable(user).isPresent()) {
      return Optional.of(modelMapper.map(user, UserDto.class));
    }
    return Optional.of(createNewUser(mobile));
  }

  private UserDto createNewUser(String mobile) {
    Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow();

    User user = new User();
    user.setMobileNumber(mobile);
    user.getRoles().add(userRole);

    user = userRepository.save(user);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public UserDetails toUserDetails(UserDto userDto) {
    Set<GrantedAuthority> authorities =
        userDto.getRoles().stream()
            .flatMap(r -> r.getPermissions().stream())
            .map(p -> new SimpleGrantedAuthority(p.getName()))
            .collect(Collectors.toSet());

    return org.springframework.security.core.userdetails.User.withUsername(
            userDto.getMobileNumber())
        .password("N/A")
        .authorities(authorities)
        .build();
  }

  @Override
  public Optional<UserDto> findByMobileNumber(String mobileNumber) {
    User user = userRepository.findByMobileNumber(mobileNumber);
    return Optional.ofNullable(modelMapper.map(user, UserDto.class));
  }
}
