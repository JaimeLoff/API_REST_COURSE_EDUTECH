# 📘 API de Gestión de Cursos

Esta API permite gestionar cursos mediante operaciones CRUD (Crear, Leer, Actualizar, Eliminar).  
Es ideal para sistemas educativos o plataformas de capacitación que requieren administrar información de cursos y docentes.

---

## 📁 Tabla de Contenidos

- [🚀 Tecnologías Usadas](#-tecnologías-usadas)  
- [🧹 API v1 — CRUD sin HATEOAS](#-api-v1--crud-sin-hateoas)  
- [🚀 API v2 — CRUD con HATEOAS](#-api-v2--crud-con-hateoas)  
- [🔍 Documentación Swagger](#-documentación-swagger)  
- [🐳 Docker](#-docker)  
- [🧪 Pruebas Implementadas](#-pruebas-implementadas)  
- [⚙️ Configuración de Entornos](#⚙️-configuración-de-entornos)  
- [📞 Integración con API externa de Usuarios](#-integración-con-api-externa-de-usuarios)  
- [🔠 Ejecución Local](#-ejecución-local)  
- [📞 Autor](#-autor)  

---

## 🚀 Tecnologías Usadas

- Java 17  
- Spring Boot 3+  
- Spring Data JPA  
- MySQL Database  
- Swagger para documentación  
- Docker para contenerización  
- Lombok  
- Bean Validation con `@Valid`  
- Spring HATEOAS  
- Spring RestTemplate  

---

## 🧹 API v1 — CRUD sin HATEOAS

### Endpoints v1

- **GET** `/api/v1/course`  
  Obtiene todos los cursos.

- **GET** `/api/v1/course/{id}`  
  Obtiene un curso por ID.

- **POST** `/api/v1/course`  
  Agrega un nuevo curso.

- **PUT** `/api/v1/course/{id}`  
  Actualiza un curso existente.

- **DELETE** `/api/v1/course/{id}`  
  Elimina un curso.

### Ejemplo JSON POST/PUT body

```json
{
  "nombreCurso": "Java Básico",
  "tipoCurso": "Online",
  "nivelCurso": "Inicial",
  "cantidadEstudiantes": 30,
  "docenteCargo": "Juan Pérez",
  "duracionCurso": 40,
  "precioCurso": 100
}
```

### Ejemplos de respuestas JSON v1

**GET /api/v1/course**

```json
[
  {
    "idCurso": "a1b2c3d4-e5f6-7890-1234-56789abcdef0",
    "nombreCurso": "Java Básico",
    "tipoCurso": "Online",
    "nivelCurso": "Inicial",
    "cantidadEstudiantes": 30,
    "docenteCargo": "Juan Pérez",
    "duracionCurso": 40,
    "precioCurso": 100.0
  },
  {
    "idCurso": "a2b3c4d5-e6f7-8901-2345-67890abcdef1",
    "nombreCurso": "Spring Boot Intermedio",
    "tipoCurso": "Presencial",
    "nivelCurso": "Intermedio",
    "cantidadEstudiantes": 25,
    "docenteCargo": "Ana Gómez",
    "duracionCurso": 60,
    "precioCurso": 150.0
  }
]
```

**GET /api/v1/course/{id}**

```json
{
  "idCurso": "a1b2c3d4-e5f6-7890-1234-56789abcdef0",
  "nombreCurso": "Java Básico",
  "tipoCurso": "Online",
  "nivelCurso": "Inicial",
  "cantidadEstudiantes": 30,
  "docenteCargo": "Juan Pérez",
  "duracionCurso": 40,
  "precioCurso": 100.0
}
```

**POST /api/v1/course (response)**

```json
{
  "idCurso": "b1b2b3b4-b5b6-b7b8-b9b0-c1c2c3c4c5c6",
  "nombreCurso": "Microservicios con Spring",
  "tipoCurso": "Online",
  "nivelCurso": "Avanzado",
  "cantidadEstudiantes": 20,
  "docenteCargo": "Carlos Ruiz",
  "duracionCurso": 50,
  "precioCurso": 200.0
}
```

**Error 404 (Curso no encontrado)**

```json
{
  "message": "Course not found"
}
```

**Error 400 (Validación de datos)**

```json
{
  "timestamp": "2024-06-24T10:12:45.305+00:00",
  "status": 400,
  "errors": [
    "El nombre del curso no puede estar vacío",
    "El precio del curso no puede ser negativo"
  ]
}
```

---

## 🚀 API v2 — CRUD con HATEOAS

Soporte completo de HATEOAS para navegabilidad avanzada.

### Endpoints principales v2

- **GET** `/api/v2/cursos`  
- **GET** `/api/v2/cursos/{id}`  
- **POST** `/api/v2/cursos`  
- **PUT** `/api/v2/cursos/{id}`  
- **DELETE** `/api/v2/cursos/{id}`  

### Ejemplo de respuesta JSON para GET /api/v2/cursos

```json
{
  "_embedded": {
    "courseManagementList": [
      {
        "idCurso": "a1b2c3d4-e5f6-7890-1234-56789abcdef0",
        "nombreCurso": "Java Básico",
        "tipoCurso": "Online",
        "nivelCurso": "Inicial",
        "cantidadEstudiantes": 30,
        "docenteCargo": "Juan Pérez",
        "duracionCurso": 40,
        "precioCurso": 100.0,
        "_links": {
          "self": { "href": "http://localhost:8083/api/v2/cursos/a1b2c3d4-e5f6-7890-1234-56789abcdef0" },
          "update": { "href": "http://localhost:8083/api/v2/cursos/a1b2c3d4-e5f6-7890-1234-56789abcdef0" },
          "delete": { "href": "http://localhost:8083/api/v2/cursos/a1b2c3d4-e5f6-7890-1234-56789abcdef0" }
        }
      }
    ]
  },
  "_links": {
    "self": { "href": "http://localhost:8083/api/v2/cursos" }
  }
}
```

---

## 🔍 Documentación Swagger

Accede a Swagger UI en:  
http://localhost:8083/swagger-ui/index.html

---

## 🐳 Docker

```bash
docker build -t course-api .
docker run -p 8083:8083 course-api
```

---

## 🧪 Pruebas Implementadas

- **Controlador:** `@WebMvcTest`, `MockMvc`, `@MockBean`  
- **Servicio:** `@Mock`, `@InjectMocks`, uso de Mockito  

---

## ⚙️ Configuración de Entornos

### application-dev.properties

```properties
spring.profiles.active=dev
server.port=8083
springdoc.swagger-ui.path=/swagger-ui.html

spring.datasource.url=jdbc:mysql://${DB_ENDPOINT}:${DB_PORT}/${DB_NAME}_DEV
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# URL del microservicio de usuarios
users.api.url=http://localhost:8081
```

### application-test.properties

```properties
spring.profiles.active=test
server.port=8083
springdoc.swagger-ui.path=/swagger-ui.html

spring.datasource.url=jdbc:mysql://${DB_ENDPOINT}:${DB_PORT}/${DB_NAME}_TEST
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### application-prod.properties

```properties
spring.profiles.active=prod
server.port=8083
springdoc.swagger-ui.path=/swagger-ui.html

spring.datasource.url=jdbc:mysql://${DB_ENDPOINT}:${DB_PORT}/${DB_NAME}
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

---

## 📞 Integración con API externa de Usuarios

Se implementó una integración REST para obtener información de usuarios desde otro microservicio:

### Endpoint:

```http
GET /api/v1/course/{idCourse}/users/{userId}
```

### Configuración `RestTemplate`

```java
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

### Servicio externo:

```java
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${users.api.url}")
    private String userApiUrl;

    public UserResponseDto fetchUserById(UUID userId) {
        String url = userApiUrl + "/api/v1/users/" + userId;
        return restTemplate.getForObject(url, UserResponseDto.class);
    }
}
```

### DTO:

```java
public record UserResponseDto(
    UUID userId,
    String firstName,
    String lastName,
    LocalDate birthDate,
    String email,
    String phone,
    String address,
    String rol,
    boolean active
) {}
```

### Properties:

```properties
users.api.url=http://localhost:8081
```

### Respuesta esperada:

```json
{
  "course": {
    "idCurso": "...",
    "nombreCurso": "..."
  },
  "user": {
    "userId": "...",
    "firstName": "..."
  }
}
```

---

## 🔠 Ejecución Local

```bash
./mvnw spring-boot:run
```

Accede a: http://localhost:8083/swagger-ui/index.html

---

## 📞 Autor

**Nombre:** Jaime Loff Miralles
