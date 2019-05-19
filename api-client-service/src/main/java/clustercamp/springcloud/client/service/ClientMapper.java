package clustercamp.springcloud.client.service;

import clustercamp.springcloud.base.dto.ClientDto;
import clustercamp.springcloud.client.domain.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

  ClientDto convert(Client client);

  Client convert(ClientDto clientDto);
}
