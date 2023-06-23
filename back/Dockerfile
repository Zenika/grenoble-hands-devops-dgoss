FROM maven:3.9-eclipse-temurin-11 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn verify --fail-never
COPY . /app
RUN mvn install -DskipTests

FROM adoptopenjdk:11-jre-hotspot
RUN apt install curl
COPY --from=builder /app/target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
