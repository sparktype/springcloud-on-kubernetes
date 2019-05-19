FROM amazoncorretto:8
LABEL name="Leonardo Park"
ADD ./build/libs/spring-cloud-oauth.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]