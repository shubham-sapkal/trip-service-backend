
# Trip Service
trip-service-backend is a Spring Boot-based backend service designed to manage trip-related details efficiently. 
The system allows users to register, authenticate, and perform various trip-related operations based on their assigned roles. 
Users without roles can register but have limited access until granted a role. Once assigned a role, users can register vehicles, create trips, and define routes, ensuring smooth trip management.



## Features
- **User Registration & Authentication:** Users can register and log in to receive a role-based authentication token.

- **Role-Based Access Control (RBAC):** Users must have an assigned role to perform trip-related operations.

- **Vehicle Management:** Users with roles can register new vehicles and store relevant details.

- **Trip Management:** Authorized users can create trips using registered vehicles.

- **Route Management:** Users with roles can define and manage routes for trips.



## API Reference

USERS API - [[ LINK TO USERS API DOCS ]](https://github.com/shubham-sapkal/trip-service-backend/tree/master/src/main/java/com/trip_service/trip_service/users)

ROUTES API - [[ LINK TO ROUTES API DOCS ]](https://github.com/shubham-sapkal/trip-service-backend/tree/master/src/main/java/com/trip_service/trip_service/route)


## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory

```bash
  cd my-project
```

Install dependencies

```bash
  mvnw clean install
```

Start the server

```bash
  mvnw spring-boot:run
```


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`DB_URL`  <- Database URL (Example: localhost:5432)

`DB_NAME` <- Database Name (Example: postgres )

`DB_USERNAME` <- Database Username (Example: postgres)

`DB_PASSWORD`<- Database Password (Example: Root) 

`JWT_SIGNING_KEY` <-  JWT SIGNING KEY (Can Be Anything, Example: asdsdfsefwerfrgfdgergdefggerregr)



