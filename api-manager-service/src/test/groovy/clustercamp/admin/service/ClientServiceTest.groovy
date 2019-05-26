package clustercamp.admin.service


import clustercamp.base.dto.ClientDto
import spock.lang.Specification

class ClientServiceTest extends Specification {

  clustercamp.admin.repository.ClientRepository repository = Mock()

  ClientService service = new ClientService(repository)

  clustercamp.admin.domain.Client client

  ClientDto clientDto

  def setup() {
    client = new clustercamp.admin.domain.Client(id: 1L, clientId: 'leonardo')
    clientDto = new ClientDto(clientId: 'leonardo')
  }

  def "test detail"() {
    given:
    def clientId = 'leonardo'

    when:
    service.detail(clientId)

    then:
    1 * repository.findByClientId({ it == clientId }) >> Optional.of(client)
  }

  def "test create"() {
    given:
    def clientId = 'leonardo'

    when:
    service.create(client)

    then:
    1 * repository.save({ it.clientId == clientId }) >> client
  }

  def "test modify"() {
    given:
    def id = 1L
    def clientId = 'leonardo2'
    client.setClientId(clientId)

    when:
    service.modify(id, client)

    then:
    1 * repository.findById({ it == 1L }) >> Optional.of(client)
    1 * repository.save({ it.clientId == clientId }) >> client
  }

  def "test remove"() {
    given:
    def id = 1

    when:
    service.remove(id)

    then:
    1 * repository.deleteById({ it == 1 })
  }

}
