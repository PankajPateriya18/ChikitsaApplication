FROM maven:3-eclipse-temurin-17 AS build
copy . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=build /target/*.jar Chikitsa-Application-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT [ "java", "-jar", "Chikitsa-Application-0.0.1-SNAPSHOT.jar" ]