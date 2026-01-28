# Challenge_LiterAlura

Literalura — Aplicación de Consola con Spring Boot

Aplicación de consola desarrollada en Java 17 utilizando Spring Boot y Spring Data JPA, que consume la API REST Gutendex para la búsqueda de libros y persiste la información en una base de datos PostgreSQL, aplicando principios de arquitectura por capas, uso de DTOs y manejo de relaciones entre entidades.

# Objetivo del Proyecto

Desarrollar una aplicación backend que permita consultar libros desde una API externa, almacenarlos localmente en una base de datos relacional y ofrecer distintas opciones de consulta mediante un menú interactivo en consola.
El proyecto forma parte de un desafío práctico de backend con Java y Spring Boot.

# Arquitectura del Proyecto

El proyecto está organizado siguiendo una arquitectura por capas:

    com.challenge.literalura
    │
    ├── principal
    │   └── Principal.java
    │
    ├── model
    │   ├── Author.java
    │   └── Book.java
    │
    ├── repository
    │   ├── AuthorRepository.java
    │   └── BookRepository.java
    │
    ├── service
    │   ├── LibroService.java
    │   ├── GutendexClient.java
    │   └── ConvierteDatos.java
    │  
    ├── dto
    │   ├── GutendexResponse.java
    │   └── BookDTO.java
    │
    └── LiteraluraApplication.java

Cada capa tiene una responsabilidad clara:

- Model: entidades JPA
- Repository: acceso a datos
- Service: lógica de negocio
- DTO: desacoplamiento entre API externa y entidades
- Principal: interacción con el usuario por consola

# Tecnologías Utilizadas

- Java 25
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven
- Jackson Databind
- API Gutendex
- IntelliJ IDEA
- Git y GitHub

  # Consumo de API Externa

  Se utiliza la API Gutendex, basada en el Proyecto Gutenberg, para la búsqueda de libros por título.

# Endpoint utilizado:

    https://gutendex.com/books/?search={titulo}

La respuesta JSON se procesa mediante ObjectMapper de Jackson, convirtiéndose primero en DTOs y luego en entidades persistentes.

# Persistencia de Datos

Base de Datos:

- PostgreSQL
- Gestión mediante Spring Data JPA

Entidades Principales

# Book

- id
- title
- language
- downloadCount
- author (ManyToOne)

# Author

- id
- name
- birthYear
- deathYear
- books (OneToMany)

# Relaciones

- Un autor puede tener múltiples libros
- Un libro pertenece a un solo autor


# Configuración JPA

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

Hibernate se encarga automáticamente de la creación y actualización del esquema.

# Funcionalidades Implementadas

La aplicación presenta un menú interactivo por consola:

    1 - Buscar libro por título (API)
    2 - Listar libros registrados
    3 - Listar autores registrados
    4 - Listar autores vivos en un año
    5 - Listar libros por idioma
    0 - Salir

Detalles técnicos:

- Validación para evitar inserción de libros duplicados
- Persistencia automática de autores relacionados
- Consultas personalizadas en los repositorios
- Manejo de respuestas vacías de la API

# Manejo de Errores

- Mensaje informativo cuando un libro no se encuentra en la API
- Control de duplicados antes de persistir datos
- Validación de entradas del usuario
- Manejo de excepciones en la conversión de datos JSON

