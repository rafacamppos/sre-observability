FROM openjdk:latest
EXPOSE 8080
VOLUME /tmp
WORKDIR /code

RUN apt-get update
RUN apt-get install -y maven

ADD pom.xml /code/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src /code/src
RUN ["mvn", "package"]
ADD target/*.jar app.jar

RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd-file:/dev/./urandom","-jar","/app.jar"]