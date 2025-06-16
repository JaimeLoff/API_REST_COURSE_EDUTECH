# Etapa 1: Compilación del proyecto
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /cursos_app

# Copia el descriptor de dependencias y descarga dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el resto del código solo después
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen liviana de ejecución
FROM eclipse-temurin:17-jre
WORKDIR /cursos_app
COPY --from=build /cursos_app/target/*.jar cursos_app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "cursos_app.jar"]
