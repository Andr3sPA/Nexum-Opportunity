# Nexum-Opportunity

## Descripción

Este es el microservicio de oportunidades para la aplicación Nexum, desarrollado con Java y Spring Boot.

## Tecnologías y versiones

- **Java**: 21
- **Spring Boot**: 3.4.5
- **Gradle plugins**: Jacoco 0.8.12, SpringDoc OpenAPI Gradle Plugin 1.9.0
- **Dependencias clave**: Lombok, MapStruct 1.6.3, SpringDoc OpenAPI 2.8.5, Spring Boot Actuator, PostgreSQL, H2
- **Arquitectura**: Hexagonal (domain/application/infrastructure)

## Variables de entorno

Crea un archivo `.env` en la raíz del proyecto con las siguientes variables (opcionales, tienen valores por defecto):

- `SPRING_PROFILES_ACTIVE`: Perfil activo (default: dev)
- `DB_HOST`: Host de la base de datos (default: localhost)
- `DB_PORT`: Puerto de la base de datos (default: 5432)
- `DB_NAME`: Nombre de la base de datos (default: nexum)
- `DB_USER`: Usuario de la base de datos (default: nexum_user)
- `DB_PASSWORD`: Contraseña de la base de datos (default: password)
- `DB_SCHEMA`: Esquema de la base de datos (default: opportunity)
- `DB_DDL`: Estrategia DDL de Hibernate (default: update)
- `APP_PATH`: Contexto de la aplicación (default: /nexum)
- `APP_PORT`: Puerto del servidor (default: 8120)
- `NEXUM_PROFILE_URL`: URL del microservicio de perfil (default: http://localhost:8100/nexum/v1)
- `NEXUM_CATALOG_URL`: URL del microservicio de catálogo (default: http://localhost:8110/nexum/v1)
- `NEXUM_FRONT_ORIGIN`: Origen permitido para CORS

## Configuración y ejecución

### Con Docker
1. Construye la imagen: `docker build -t nexum-opportunity .`
2. Ejecuta el contenedor: `docker run -p 8120:8120 nexum-opportunity`

### Con Gradle
1. Construye el proyecto: `./gradlew build`
2. Ejecuta la aplicación: `./gradlew bootRun`