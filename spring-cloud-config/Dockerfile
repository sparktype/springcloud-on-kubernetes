FROM amazoncorretto:8
LABEL name='Leonardo Park'
ADD ./build/libs/config.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]