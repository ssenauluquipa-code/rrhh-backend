# Etapa 1: Compilar con Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Crear imagen ligera con solo el JAR
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copia el JAR generado del proyecto rrhh-backend
COPY --from=build /app/target/rrhh-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Configura Spring Boot para escuchar en el puerto dinámico
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:-8080}", "--logging.level.root=DEBUG"]