FROM openjdk:17-oracle
WORKDIR /app
EXPOSE 8080
COPY build/libs/MessageService-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "MessageService-0.0.1-SNAPSHOT.jar"]
