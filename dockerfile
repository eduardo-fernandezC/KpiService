#1Etapa de build
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

#Copiar dependencias primero (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

#3Copiar código fuente
COPY src ./src

#Construir el JAR
RUN mvn clean package -DskipTests

#Etapa final (liviana)
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

#Copiar el jar generado
COPY --from=build /app/target/*.jar app.jar

#Puerto de Spring Boot
EXPOSE 8092

#Ejecutar la app
CMD ["java", "-jar", "app.jar"]