FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=target/SpringBootRegistrationLogin-1.0.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
