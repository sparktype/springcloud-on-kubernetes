package clustercamp.base.dto;

import clustercamp.base.jpa.CreateAware;
import clustercamp.base.jpa.ModifyAware;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ClientDto extends BaseDTO implements CreateAware, ModifyAware {

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
