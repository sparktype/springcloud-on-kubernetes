package clustercamp.sale.api;

import java.util.Objects;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Version")
public class VersionController {

  @Autowired(required = false)
  private BuildProperties buildProperties;


  @GetMapping("/version")
  public ResponseEntity<String> version() {
    var version = Objects.isNull(buildProperties) ? "1.0.0" : buildProperties.getVersion();
    return ResponseEntity.ok(version);
  }
}
