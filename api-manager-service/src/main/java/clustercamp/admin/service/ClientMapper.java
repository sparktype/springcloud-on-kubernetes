package clustercamp.admin.service;

import clustercamp.admin.domain.Client;
import clustercamp.base.dto.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

  ClientDto convert(Client client);

  Client convert(ClientDto clientDto);
}
