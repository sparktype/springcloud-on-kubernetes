package clustercamp.springcloud.oauth.details;

import clustercamp.springcloud.base.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;


@Component
public class RestClientDetailsService implements ClientDetailsService {

  @Autowired
  private RestTemplate restTemplate;


  @Override
  public ClientDetails loadClientByClientId(String clientId) {
    BaseClientDetails clientDetails = new BaseClientDetails();

    UriComponents uriComponents = UriComponentsBuilder
                                    .fromUriString("http://CLIENT-SERVICE/api/v1/auth/client/{clientId}")
                                    .buildAndExpand(clientId)
                                    .encode();

    HttpEntity<ClientDto> entity = restTemplate.getForEntity(uriComponents.toUri(), ClientDto.class);

    if (!entity.hasBody()) {
      throw new InvalidClientException("클라이언트를 찾을 수 없습니다.");
    }

    ClientDto client = entity.getBody();
    clientDetails.setClientId(clientId);
    clientDetails.setClientSecret(client != null ? client.getClientSecret() : "");
    if (client != null) {
      clientDetails.setResourceIds(Collections.singletonList(client.getResourceIds()));
      clientDetails.setScope(Collections.singletonList(client.getScope()));
    }
    clientDetails.setAuthorizedGrantTypes(
      StringUtils.commaDelimitedListToSet(client != null ? client.getAuthorizedGrantTypes() : null));
    clientDetails.setAuthorities(
      AuthorityUtils.commaSeparatedStringToAuthorityList(client.getAuthorities()));
    clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
    clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());

    return clientDetails;
  }

}
