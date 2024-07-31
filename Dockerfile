FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/aecode1-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} aecode1.jar
ENTRYPOINT ["java","-jar","/aecode1.jar"]
