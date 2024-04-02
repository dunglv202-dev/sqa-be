FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /usr/src/app
COPY pom.xml pom.xml
COPY ./src ./src
RUN --mount=type=cache,target=/root/.m2 mvn clean package

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]