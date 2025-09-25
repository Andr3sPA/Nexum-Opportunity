FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/build/libs/opportunity-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8120
ENTRYPOINT ["java", "-jar", "app.jar"]