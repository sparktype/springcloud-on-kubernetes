package clustercamp.springcloud.client.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Client {

  @Id
  @NonNull
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NonNull
  @Column(unique = true, nullable = false)
  private String clientId;

  @Column(nullable = false)
  private String clientSecret;

  private String scope;

  private String resourceIds;

  private String authorizedGrantTypes;

  private String registeredRedirectUris;

  private String autoApproveScopes;

  private String authorities;

  private Integer accessTokenValiditySeconds;

  private Integer refreshTokenValiditySeconds;

}
