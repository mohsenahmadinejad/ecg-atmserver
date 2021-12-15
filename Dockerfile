FROM openjdk:8-alpine
MAINTAINER "Mohsen Ahmadinejad"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} atm-server.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/atm-server.jar"]