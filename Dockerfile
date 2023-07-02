# Use a base image with Java 17 installed
FROM openjdk:17
WORKDIR /app

COPY . /app

RUN ./mvnw package

EXPOSE 8080

CMD ["java", "-jar", "/app/target/demo-0.0.1-SNAPSHOT.jar"]
