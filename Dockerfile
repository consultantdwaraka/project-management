FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT [“java”,”-Dspring.data.mongodb.uri=mongodb://mongocontainer/test”, “-Djava.security.egd=file:/dev/./urandom”,”-jar”,”/app.jar”]
 
