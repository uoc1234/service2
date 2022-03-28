FROM openjdk:11-jdk-slim-buster
ADD build/libs/*-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
