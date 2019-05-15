FROM amazoncorretto:8
LABEL maintainer="Leonardo Park"
ADD ./build/libs/client-service.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]