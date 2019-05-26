package clustercamp.user.service;

import clustercamp.base.dto.UserDTO;
import clustercamp.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDTO convert(User user);
  User convert(UserDTO userDto);
}
