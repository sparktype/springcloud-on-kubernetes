FROM arm64v8/openjdk:11.0.3-jre-slim

ENV ZIPKIN_REPO https://repository.apache.org/content/repositories/releases/
ENV ZIPKIN_VERSION 2.14.0

# Add environment settings for supported storage types
COPY zipkin /zipkin/
WORKDIR /zipkin


RUN apk add unzip curl --no-cache && \
  curl -SL $ZIPKIN_REPO/org/apache/zipkin/zipkin-server/$ZIPKIN_VERSION/zipkin-server-$ZIPKIN_VERSION-exec.jar > zipkin-server.jar && \
  # don't break when unzip finds an extra header https://github.com/openzipkin/zipkin/issues/1932
  unzip zipkin-server.jar ; \
  rm zipkin-server.jar && \
  apk del unzip


# Use to set heap, trust store or other system properties.
ENV JAVA_OPTS -Djava.security.egd=file:/dev/./urandom
# 3rd party modules like zipkin-aws will apply profile settings with this
ENV MODULE_OPTS=

# Add environment settings for supported storage types
COPY --from=0 /zipkin/ /zipkin/
WORKDIR /zipkin

RUN ["/busybox/sh", "-c", "ln -s /busybox/* /bin"]

EXPOSE 9410 9411

ENTRYPOINT ["/busybox/sh", "run.sh"]
