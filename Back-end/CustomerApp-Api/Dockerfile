# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /Back-end/CustomerApp-Api/src
COPY pom.xml /Back-end/CustomerApp-Api
RUN mvn -f /Back-end/CustomerApp-Api/pom.xml clean package

# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /Back-end/CustomerApp-Api/target/api-0.0.1-SNAPSHOT.jar /usr/local/lib/api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/api.jar"]