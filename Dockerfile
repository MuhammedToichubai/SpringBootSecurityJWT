FROM openjdk:22 as build
WORKDIR /app
COPY . ./
RUN microdnf install findutils
RUN ./gradlew build -x test

FROM openjdk:22-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/SpringBootSecurityJWT-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "SpringBootSecurityJWT-0.0.1-SNAPSHOT.jar"]
EXPOSE 9797

