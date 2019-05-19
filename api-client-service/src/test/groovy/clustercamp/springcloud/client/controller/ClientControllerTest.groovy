package clustercamp.springcloud.client.controller

import clustercamp.springcloud.base.dto.ClientDto
import clustercamp.springcloud.client.domain.Client
import clustercamp.springcloud.client.service.ClientMapper
import clustercamp.springcloud.client.service.ClientService
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.mock.DetachedMockFactory

//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors

@WebMvcTest
@ContextConfiguration
class ClientControllerTest extends Specification {

  @Autowired
  MockMvc mvc

  @Autowired
  ClientService service

  Client client

  ClientDto dto

  def setup() {
    client = new Client(id: 1L, clientId: 'leonaro')
    dto = new ClientDto(id: 1L, clientId: 'leonardo')

  }

  def "When request client data should return 200"() {
    given:
    def request = MockMvcRequestBuilders.get("/api/client?clientId={clientId}", 'leonardo')
//      .with(SecurityMockMvcRequestPostProcessors.user('user').roles('USER'))
//      .with(SecurityMockMvcRequestPostProcessors.csrf())

    when:
    def response = mvc.perform(request).andReturn().response

    then:
    1 * service.detail('leonardo') >> client
    response.status == HttpStatus.OK.value()
  }

  def "When request save client data should return 201"() {
    given:
    def request = MockMvcRequestBuilders.post("/api/client/")
//      .with(SecurityMockMvcRequestPostProcessors.user('user').roles('USER'))
//      .with(SecurityMockMvcRequestPostProcessors.csrf())
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(JsonOutput.toJson(dto))

    when:
    def response = mvc.perform(request).andReturn().response

    then:
    1 * service.create(_) >> client
    response.status == HttpStatus.CREATED.value()
  }

  def "When request modify client data should return 201"() {
    given:
    def id = 1

    def request = MockMvcRequestBuilders.put("/api/client/{id}", id)
//      .with(SecurityMockMvcRequestPostProcessors.user('user').roles('USER'))
//      .with(SecurityMockMvcRequestPostProcessors.csrf())
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(JsonOutput.toJson(dto))

    when:
    def response = mvc.perform(request).andReturn().response

    then:
    1 * service.modify(id, _) >> client

    response.status == HttpStatus.CREATED.value()
  }


//  @WithMockUser(username = 'leonardo', roles = ['USER'])
  def "When request remove client data should return 204"() {
    given:
    def id = 1
    def request = MockMvcRequestBuilders.delete("/api/client/{id}", id)
//      .with(SecurityMockMvcRequestPostProcessors.csrf())

    when:
    def response = mvc.perform(request).andReturn().response

    then:
    1 * service.remove(id)
    response.status == HttpStatus.NO_CONTENT.value()
  }

  @TestConfiguration
  static class MockConfig {
    def factory = new DetachedMockFactory()

    @Bean
    ClientService clientService() {
      return factory.Mock(ClientService)
    }

    @Bean
    ClientMapper clientMapper() {
      return new ClientMapper()
    }
  }

}
