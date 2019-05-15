package clustercamp.springcloud.user.service;

import clustercamp.springcloud.base.dto.UserDto;
import clustercamp.springcloud.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto convert(User user);

  User convert(UserDto userDto);

}
