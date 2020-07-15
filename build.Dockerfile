FROM --platform=amd64 gradle:jdk14 AS builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 

FROM openjdk:16-slim

VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS

COPY --from=builder /home/gradle/src/build/libs/*.jar list-manager.jar
ENTRYPOINT exec java $JAVA_OPTS -jar list-manager.jar