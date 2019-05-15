FROM amazoncorretto:8
LABEL name="Leonardo Park"
ADD ./build/libs/hystrix.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]