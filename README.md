
# 📘 API de Gestión de Cursos

Esta API permite gestionar cursos mediante operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

---

## 🚀 Tecnologías Usadas

- Java 17  
- Spring Boot 3+  
- Spring Data JPA  
- Oracle Database  
- Swagger para documentación  
- Docker para contenerización  
- Lombok  
- Bean Validation con `@Valid`

---

## 📚 Endpoints

### ✅ Obtener todos los cursos

**GET** `/api/v1/course`

- Devuelve una lista de todos los cursos.

---

### 🔍 Obtener un curso por ID

**GET** `/api/v1/course/{id}`

- Parámetro: `id` (UUID)  
- Devuelve los datos del curso o `404 Not Found` si no existe.

---

### ➕ Agregar un curso

**POST** `/api/v1/course`

- Body (JSON):

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

- Validaciones automáticas con `@Valid`:  
  - `nombreCurso` no puede estar vacío.  
  - `cantidadEstudiantes` y `precioCurso` no pueden ser negativos.  
- Devuelve el curso creado con su `idCurso`.  
- Responde `400 Bad Request` si los datos no cumplen validaciones.

---

### 📝 Actualizar un curso

**PUT** `/api/v1/course/{id}`

- Parámetro: `id` (UUID)  
- Body igual que POST.  
- Validaciones automáticas con `@Valid` igual que POST.  
- Devuelve el curso actualizado o `404 Not Found` si no existe el curso.

---

### ❌ Eliminar un curso

**DELETE** `/api/v1/course/{id}`

- Parámetro: `id` (UUID)  
- Devuelve código `200 OK` si fue eliminado.  
- Devuelve `404 Not Found` si no existe el curso.

---

## 🔍 Documentación Swagger

Accede a Swagger UI en:

```
http://localhost:8083/swagger-ui/index.html
```

Desde ahí podés probar todos los endpoints directamente.

---

## 🐳 Docker

### Construir imagen

```bash
docker build -t course-api .
```

### Correr contenedor

```bash
docker run -p 8083:8083 course-api
```

---

## 🥪 Pruebas recomendadas

Prueba los siguientes endpoints con Swagger UI o Postman y toma capturas:

- GET `/api/v1/course`  
- GET `/api/v1/course/{id}`  
- POST `/api/v1/course`  
- PUT `/api/v1/course/{id}`  
- DELETE `/api/v1/course/{id}`  

---

## 📌 Recomendaciones Finales

- Usa `@Valid` para validación automática en POST y PUT.  
- Controla errores con respuestas HTTP claras (400, 404, 200).  
- Documenta bien con Swagger para facilitar pruebas.  
- Mantén separada la lógica en Controller, Service y Repository.  
- Ajusta configuración para Oracle en `application.properties` o `application.yml`.  

Ejemplo básico para Oracle en `application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/xe
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver

spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
spring.jpa.hibernate.ddl-auto=update
```

---

## 📞 Autor

- Nombre: Jaime Loff
