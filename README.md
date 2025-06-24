
# 📘 API de Gestión de Cursos

Esta API permite gestionar cursos mediante operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

## 🚀 Tecnologías Usadas
- Java 17
- Spring Boot 3+
- Spring Data JPA
- MySQL Database
- Swagger para documentación
- Docker para contenerización
- Lombok
- Bean Validation con `@Valid`

## 📚 Endpoints

### ✅ Obtener todos los cursos
`GET /api/v1/course`

### 🔍 Obtener un curso por ID
`GET /api/v1/course/{id}`

**Parámetro:** `id` (UUID)  
Devuelve los datos del curso o 404 Not Found si no existe.

### ➕ Agregar un curso
`POST /api/v1/course`

**Body (JSON):**
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

Validaciones automáticas con `@Valid`:
- `nombreCurso` no puede estar vacío.
- `cantidadEstudiantes` y `precioCurso` no pueden ser negativos.

### 📝 Actualizar un curso
`PUT /api/v1/course/{id}`

**Parámetro:** `id` (UUID)  
**Body:** igual que POST  
Devuelve el curso actualizado o 404 Not Found si no existe.

### ❌ Eliminar un curso
`DELETE /api/v1/course/{id}`

**Parámetro:** `id` (UUID)  
Devuelve código 200 OK si fue eliminado.  
Devuelve 404 Not Found si no existe el curso.

## 🔍 Documentación Swagger
Accede a Swagger UI en:  
[http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)

## 🐳 Docker

### Construir imagen
```bash
docker build -t course-api .
```

### Correr contenedor
```bash
docker run -p 8083:8083 course-api
```

## 🧪 Pruebas Implementadas

Se han desarrollado tests unitarios para los siguientes componentes:

### ✅ Controlador (`CourseManagementController`)
- Se testean los endpoints `GET`, `POST`, `PUT` y `DELETE`.
- Uso de `@WebMvcTest` y `MockMvc`.
- Uso de `@MockBean` para simular el servicio.

### ✅ Servicio (`CourseManagementService`)
- Se testean los métodos: `getAllCourse`, `getCourseById`, `addCourse`, `updateCourse`, `deleteCourse`.
- Uso de `@Mock` y `@InjectMocks`.
- Verificación de comportamiento con `Mockito`.

## ⚙️ Configuración de Entornos

### 📁 application-dev.properties
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

### 📁 application-test.properties
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

### 📁 application-prod.properties
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
## 📦 Ejemplos de Respuestas JSON

### ✅ GET /api/v1/course

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

### 🔍 GET /api/v1/course/{id}

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

### ➕ POST /api/v1/course (Response)

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

### ❌ Error 404 (Curso no encontrado)

```json
{
  "message": "Course not found"
}
```

### ❌ Error 400 (Validación de datos)

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
## 📞 Autor
**Nombre:** Jaime Loff Miralles
