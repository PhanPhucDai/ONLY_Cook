FROM openjdk:22-jdk-slim

WORKDIR /app

COPY . /app

RUN chmod +x mvnw && ./mvnw clean package -DskipTests
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/*.jar"]
