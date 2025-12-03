# Proyecto: API RESTful para Gestión de Bibliotecas Universitarias

Este proyecto implementa un **backend completo** para la gestión de bibliotecas universitarias usando **Java y Spring Boot**, integrando **persistencia en base de datos relacional**, **seguridad con JWT**, y control de acceso por roles (`ADMIN` y `USER`).  

Permite a los usuarios y administradores:

- Registrar y consultar libros.
- Gestionar préstamos y devoluciones.
- Registrar usuarios y roles.
- Controlar el acceso a funcionalidades según rol.
- Documentar y probar endpoints con Postman/Swagger.

---

## Logros Esperados

- Emplea fundamentos de Java y Spring Boot con sintaxis clara y tipada.
- Configura proyectos Spring Boot siguiendo buenas prácticas de arquitectura y principios de microservicios.
- Utiliza JPA, Hibernate y Spring Data para persistencia de datos.
- Implementa seguridad JWT con roles para autenticación sin estado y autorización.
- Protege rutas y valida datos de forma centralizada.
- Permite pruebas controladas de errores para evidenciar manejo de excepciones.

---

## Tecnologías Utilizadas

- **Lenguaje:** Java 17  
- **Framework:** Spring Boot  
- **Seguridad:** Spring Security + JWT  
- **Persistencia:** JPA, Hibernate, Spring Data JPA  
- **Base de Datos:** MySQL / PostgreSQL (configurable)  
- **Documentación:** Swagger / Postman  
- **Build:** Maven  

---

## Requisitos

1. Java 17 o superior  
2. Maven 3.8+  
3. Base de datos MySQL o PostgreSQL  
4. Postman o navegador para pruebas  

---

## Instalación y Ejecución

1. Clonar el repositorio:
```bash
git clone https://github.com/alexis20010211/BibliotecaApi.git
cd BibliotecaApi

2. Configurar base de datos en application.properties.

3. Ejecutar la aplicación:

Ejemplos de Uso (Endpoints y JSON)
1️⃣ Autenticación

Login ADMIN:

POST /auth/login
Body:
{
  "username": "admin",
  "password": "admin123"
}


Login USER:

POST /auth/login
Body:
{
  "username": "user",
  "password": "user123"
}


Guardar el token JWT para usar en los demás endpoints.

2️⃣ Libros

Crear libro (solo ADMIN)

POST /api/libros
Headers: Authorization: Bearer <TOKEN_ADMIN>
Body:
{
  "titulo": "Clean Code",
  "autor": "Robert Martin",
  "anio": 2008
}


Listar libros (ADMIN y USER)

GET /api/libros
Headers: Authorization: Bearer <TOKEN_ADMIN o TOKEN_USER>


Actualizar libro (solo ADMIN)

PUT /api/libros/{id}
Headers: Authorization: Bearer <TOKEN_ADMIN>
Body:
{
  "titulo": "Clean Architecture",
  "autor": "Robert Martin",
  "anio": 2017
}


Eliminar libro (solo ADMIN)

DELETE /api/libros/{id}
Headers: Authorization: Bearer <TOKEN_ADMIN>

3️⃣ Usuarios

Listar usuarios (solo ADMIN)

GET /api/usuarios
Headers: Authorization: Bearer <TOKEN_ADMIN>


Crear usuario (solo ADMIN)

POST /api/usuarios
Headers: Authorization: Bearer <TOKEN_ADMIN>
Body:
{
  "username": "nuevoUser",
  "password": "user123",
  "email": "user@mail.com",
  "roles": ["USER"]
}

4️⃣ Préstamos

Registrar préstamo (solo ADMIN)

POST /api/prestamos/registrar?usuarioId=2&libroId=12
Headers: Authorization: Bearer <TOKEN_ADMIN>


Devolver préstamo (solo ADMIN)

POST /api/prestamos/devolver/{prestamoId}
Headers: Authorization: Bearer <TOKEN_ADMIN>


Listar préstamos de un usuario (USER)

GET /api/prestamos/usuario/{usuarioId}
Headers: Authorization: Bearer <TOKEN_USER>


Listar todos los préstamos (ADMIN)

GET /api/prestamos
Headers: Authorization: Bearer <TOKEN_ADMIN>


Ver préstamo específico

GET /api/prestamos/{id}
Headers: Authorization: Bearer <TOKEN_ADMIN>

5️⃣ Manejo de Errores

Intentar crear libro con token USER → 403 Forbidden

Intentar devolver préstamo ya devuelto → 400 Bad Request

Buscar libro o préstamo inexistente → 404 Not Found


