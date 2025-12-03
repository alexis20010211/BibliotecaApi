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