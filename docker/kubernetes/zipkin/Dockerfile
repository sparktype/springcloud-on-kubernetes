FROM openjdk:8-jdk-alpine
LABEL maintainer="Leonardo Park"
RUN apk update && apk upgrade
RUN apk add bash bash-completion bash-doc
ADD zipkin.jar app.jar
EXPOSE 9411
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
