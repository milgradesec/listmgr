FROM openjdk:16-slim

VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS

ADD build/libs/list-manager-0.0.1.jar list-manager.jar
ENTRYPOINT exec java $JAVA_OPTS -jar list-manager.jar
