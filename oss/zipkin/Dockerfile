FROM arm64v8/alpine:3.10

ENV ZIPKIN_REPO https://repo1.maven.org/maven2
ENV ZIPKIN_VERSION 2.18.3

# Add environment settings for supported storage types
COPY zipkin/ /zipkin/
WORKDIR /zipkin

RUN apk add unzip curl --no-cache && \
    curl -SL $ZIPKIN_REPO/io/zipkin/zipkin-server/$ZIPKIN_VERSION/zipkin-server-$ZIPKIN_VERSION-exec.jar > zipkin-server.jar && \
    # don't break when unzip finds an extra header https://github.com/openzipkin/zipkin/issues/1932
    unzip zipkin-server.jar ; \
    rm zipkin-server.jar && \
    apk del unzip

FROM arm64v8/openjdk:11.0.5-jre-slim-buster
MAINTAINER Zipkin "https://zipkin.io/"

# Use to set heap, trust store or other system properties.
ENV JAVA_OPTS -Djava.security.egd=file:/dev/./urandom
# 3rd party modules like zipkin-aws will apply profile settings with this
ENV MODULE_OPTS=

# Add environment settings for supported storage types
COPY --from=0 /zipkin/ /zipkin/
WORKDIR /zipkin

EXPOSE 9410 9411

CMD [ "/zipkin/run.sh"]
