# 📘 API de Gestión de Cursos

Esta API permite gestionar cursos mediante operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

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
- **Spring HATEOAS** (para soporte HATEOAS en la API)

---

## 🧩 API v1 — CRUD básico (sin HATEOAS)

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

---

### Ejemplo JSON POST/PUT body (v1)

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

---

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
  "nombreCurso": "Curso Nuevo",
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

Se agregó soporte HATEOAS para mejorar la navegabilidad de la API, usando rutas con `/api/v2/cursos`.

### Endpoints principales v2

- **GET** `/api/v2/cursos`  
  Obtiene todos los cursos con enlaces HATEOAS.

- **GET** `/api/v2/cursos/{id}`  
  Obtiene un curso por ID con enlaces para operaciones posibles.

- **POST** `/api/v2/cursos`  
  Crea un nuevo curso y devuelve el recurso con enlaces.

- **PUT** `/api/v2/cursos/{id}`  
  Actualiza un curso existente y devuelve el recurso actualizado.

- **DELETE** `/api/v2/cursos/{id}`  
  Elimina un curso y devuelve mensaje confirmando la eliminación.

---

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

**Construir imagen**  
```bash
docker build -t course-api .
```

**Correr contenedor**  
```bash
docker run -p 8083:8083 course-api
```

---

## 🧪 Pruebas Implementadas

Se han desarrollado tests unitarios para los siguientes componentes:

- **Controlador (CourseManagementController)**  
  Testean los endpoints GET, POST, PUT y DELETE.  
  Uso de `@WebMvcTest` y `MockMvc`.  
  Uso de `@MockBean` para simular el servicio.

- **Servicio (CourseManagementService)**  
  Testean los métodos: `getAllCourse`, `getCourseById`, `addCourse`, `updateCourse`, `deleteCourse`.  
  Uso de `@Mock` y `@InjectMocks`.  
  Verificación de comportamiento con Mockito.

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

## 📞 Autor

**Nombre:** Jaime Loff Miralles
