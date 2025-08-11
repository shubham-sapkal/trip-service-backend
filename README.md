
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

USERS API - [[ LINK TO USERS API DOCS ]](https://github.com/shubham-sapkal/trip-service-backend/tree/master/src/main/kotlin/com/trip_service/trip_service/core/users)

Vehicles API - [[ LINK TO VEHICLES API DOCS ]](https://github.com/shubham-sapkal/trip-service-backend/tree/master/src/main/kotlin/com/trip_service/trip_service/core/vehicles)

Routes API = [[ LINK TO ROUTES API DOCS ]](https://github.com/shubham-sapkal/trip-service-backend/blob/master/src/main/kotlin/com/trip_service/trip_service/core/routes)

Trips API = [[ LINK TO TRIPS API DOCS ]](https://github.com/shubham-sapkal/trip-service-backend/tree/master/src/main/kotlin/com/trip_service/trip_service/core/trips)


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`DB_URL`  <- Database URL (Example: `localhost:5432` when running locally and `host.docker.internal:5432` when running throw docker container with postgres at local )

`DB_NAME` <- Database Name (Example: postgres )

`DB_USERNAME` <- Database Username (Example: postgres)

`DB_PASSWORD`<- Database Password (Example: Root)

`JWT_SIGNING_KEY` <-  JWT SIGNING KEY (Can Be Anything should be able to encrypt HS512 algorithm and signing key size should be greater than 512 bits, Example: asdsdfsefweasdasdasdasdasdasdassadssadasdsdasdsasrfrgfdgergdefggerregr)


## Run Locally

#### Mandatory to have:
1. Java 21 install
2. JAVA_HOME setup

Step 1: Clone the project
```bash
  git clone https://github.com/shubham-sapkal/trip-service-backend
```

Step 2: Go to the project directory
```bash
  cd trip-service-backend
```

Step 3: Install dotenv-cli (for .env)

```bash
    npm install -g dotenv-cli
```


Step 4: Install dependencies

```bash
  dotenv -e .env -- mvnw clean install
```

Step 5: Start the server

```bash
  dotenv -e .env -- mvnw spring-boot:run
```

## Run Using Docker

#### Mandatory to have:
1. Postgres
2. Docker Desktop

Step 1: Clone the Repository
```bash
    git clone https://github.com/shubham-sapkal/trip-service-backend
```

Step 2: Move to that repository
```bash
    cd trip-service-backend
```

Step 3: Create .env File

Step 4: Up trip-service-backend container
```bash
    docker compose up
```
