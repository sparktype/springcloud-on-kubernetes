package clustercamp.security.service;

import clustercamp.base.dto.UserDTO;
import clustercamp.security.details.BaseUserDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  BaseUserDetails convert(UserDTO userDTO);
}
