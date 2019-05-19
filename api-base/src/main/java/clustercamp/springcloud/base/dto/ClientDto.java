package clustercamp.springcloud.base.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ClientDto {

  private long id;

  private String clientId;

  private String clientSecret;

  private String scope;

  private String resourceIds;

  private String authorizedGrantTypes;

  private String registeredRedirectUris;

  private String autoApproveScopes;

  private String authorities;

  private Integer accessTokenValiditySeconds;

  private Integer refreshTokenValiditySeconds;

  private LocalDateTime createAt;

  private String createBy;

  private LocalDateTime modifyAt;

  private String modifyBy;

}
