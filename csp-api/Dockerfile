FROM openjdk:14

COPY build/libs/*.jar app.jar
COPY src/main/resources config

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
