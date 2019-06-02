package clustercamp.security.config

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import spock.lang.Specification

class SecurityConfigurationTest extends Specification {

  def setup() {

  }

  def "비밀번호_암호화"() {
    given:
    def encoder = new BCryptPasswordEncoder()

    when:

    def result = encoder.encode("1234")

    println(result)

    then:
    result == '$2a$10$M.ULkO11Ef6JPBhX7v3TAeA7fETRSzwYDBDHmhlU.E1xnF.C6djKW'

  }

}


