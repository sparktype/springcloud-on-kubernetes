package clustercamp.springcloud.oauth.common;

import org.springframework.security.crypto.password.PasswordEncoder;

public class BasePasswordEncoder implements PasswordEncoder {

  private static final PasswordEncoder INSTANCE = new BasePasswordEncoder();

  private BasePasswordEncoder() {
  }

  public static PasswordEncoder getInstance() {
    return INSTANCE;
  }

  @Override
  public String encode(CharSequence rawPassword) {
    return rawPassword.toString();
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return rawPassword.toString().equals(encodedPassword);
  }
}
