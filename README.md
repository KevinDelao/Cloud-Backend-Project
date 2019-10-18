# Cloud-Backend-Project

This repository contains the microservice dealing with the tracking data received from an HTC Vive. It's meant to expose REST API endpoints that allow for
storage and (maybe) retrieval of this data in a way compliant with HIPAA

# Database
We're using PostgreSQL. The following commands should be run from within the same folder that has the `docker-compose.yml`
file (e.g. `Cloud-Backend-Project`)

## Start
Starts the database container.
* `docker-compose up`

## Stop
This command just stops a currently running database.
* `docker-compose stop`

## Stop Remove
This command stops the currently running database and removes it completely. The data is gone.
* `docker-compose down`

# Swagger
The server hosts a user interface that lets us hit the REST endpoints. Hit the URL below after starting the server.

* http://localhost:8080/swagger-ui.html