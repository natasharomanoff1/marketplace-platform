FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY adapters/target/adapters-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
