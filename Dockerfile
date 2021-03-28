FROM openjdk:15-jdk-alpine

ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS

COPY app/build/libs/list-manager-all.jar listmanager.jar

ENTRYPOINT java $JAVA_OPTS -jar listmanager.jar
