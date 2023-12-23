FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd-file:/dev/./urandom","-jar","/app.jar"]