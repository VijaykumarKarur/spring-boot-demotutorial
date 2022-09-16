# Spring Boot Demo Project
______________________________________________
The project demonstrates the use of Spring Boot Framework to build RESTful CRUD API and also hibernate validations, logging, unit testing, actuator and managing spring profiles.

## Objectives
______________________________________________
1. Build CRUD API taking Department as an entity.
2. Integrating with persistent storage.
3. **_Validation_** of requests using hibernate validator.
4. Provide for **_logging_** using sl4j library.
5. **_Unit testing_** using JUnit and Mockito frameworks.
6. Enabling **_actuator_** and building custom actuator end-point.
7. Enabling use of dynamic **_spring profiles_** using multiple property files.

## Requirements
______________________________________________
1. Should be able to Save a Department.
2. Should be able to Get all Departments.
3. Should be able to Get a Department by it's Id.
4. Should be able to Delete a Department.
5. Should be able to Update a Department.
6. Should be able to Get a Department by it's Name.

## APIs
______________________________________________
1. POST http://[host]:[port]/departments/
2. GET http://[host]:[port]/departments/
3. GET http://[host]:[port]/departments/{id}
4. DELETE http://[host]:[port]/departments/{id}
5. PATCH http://[host]:[port]/departments/
6. GET http://[host]:[port]/departments/{name}








