# Prueba Técnica Módulo 4 Java Spring Boot
Cuarta evaluación técnica del Bootcamp de JAVA en HACK A BOSS

# App Agencia de Viajes

Esta es una API para la gestión de reservas de hoteles y vuelos, permitiendo a los usuarios consultar disponibilidad, realizar reservas y gestionar su información de manera eficiente.

##Tecnologías Utilizadas

- Java 17 o 23

- Spring Boot

- Spring Data JPA

- Hibernate

- Lombok

- Jackson (para serialización/deserialización JSON)

- Base de Datos MySQL 

##Endpoints

Permite la realización de operaciones de alta, baja y modificación sobre una base de datos tanto para la gestión de vuelos como para la gestión de hoteles a los empleados de la agencia que se encuentren AUTENTICADOS. 


Métodos GET, POST, PUT y DELETE


Paths Hoteles:


- POST: /agency/hotels/new
  
- PUT: /agency/hotels/edit/{id}

- DELETE: /agency/hotels/delete/{id}

- GET: /agency/hotels/{id} → Hotel en particular

- GET: /agency/hotels → Todos los hoteles



Paths Vuelos:


- POST: /agency/flights/new

- PUT: /agency/flights/edit/{id}

- DELETE: /agency/flights/delete/{id}

- GET: /agency/flights/{id} → Vuelo en particular

- GET: /agency/flights → Todos los vuelos

