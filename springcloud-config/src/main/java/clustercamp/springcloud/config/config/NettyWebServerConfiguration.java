package clustercamp.springcloud.config.config;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
import reactor.netty.http.server.HttpServer;


@Component
public class NettyWebServerConfiguration implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

  @Override
  public void customize(NettyReactiveWebServerFactory factory) {
    factory.addServerCustomizers(new NettyServerConfiguration(8888));
  }

  private static class NettyServerConfiguration implements NettyServerCustomizer {

    private final int port;

    private NettyServerConfiguration(int port) {
      this.port = port;
    }

    @Override
    public HttpServer apply(HttpServer httpServer) {
      return httpServer.port(port);
    }
  }
}

