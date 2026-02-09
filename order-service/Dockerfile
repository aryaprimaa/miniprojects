# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# cache dependencies
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# build
COPY src ./src
RUN mvn -q -DskipTests clean package

# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# security: non-root user
RUN useradd -m appuser
USER appuser

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]