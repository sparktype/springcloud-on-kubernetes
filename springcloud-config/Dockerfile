FROM openjdk:11.0-jre-slim
VOLUME /tmp
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY ${DEPENDENCY}/META-INF /app/META-INF

ENTRYPOINT ["java","-cp","app:app/lib/*","clustercamp.config.Application"]