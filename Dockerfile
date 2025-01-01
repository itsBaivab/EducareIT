FROM maven:3.8.5-openjdk-17 AS build
COPY ..
RUN mvn clean package -DskipTests
From openjdk:17.0.1-jdk-slim
copy --from=build /target/educare-0.0.1-SNAPSHOT.jar demo.jar
expose 8080
ENTRYPOINT ["java","-jar","demo".]
