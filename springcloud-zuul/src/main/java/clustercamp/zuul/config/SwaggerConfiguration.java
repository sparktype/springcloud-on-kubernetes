package clustercamp.zuul.config;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Primary
@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements SwaggerResourcesProvider {

  @Autowired
  private RouteLocator routeLocator;


  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.basePackage("clustercamp"))
      .paths(PathSelectors.any()).build();
  }

  @Override
  public List<SwaggerResource> get() {
    var resources = new ArrayList<SwaggerResource>();

    routeLocator.getRoutes().stream()
      .filter(r -> r.getId().startsWith("api-"))
      .forEach(route -> {
        var resource = new SwaggerResource();
        resource.setName(route.getId());
        resource.setLocation("/v2/api-docs");
        resources.add(resource);
      });

    return resources;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().build();
  }
}
