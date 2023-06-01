# Movie-Booking-Api

The Movie Booking API is a RESTful web service that allows users to book movie tickets online. It provides endpoints for managing movies, theaters, screenings, and bookings.


### Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- MySQL (for data storage)
- JWT (JSON Web Tokens) for authentication
- Swagger UI (for API documentation)




### ER Diagram
The following Diagram depicts the flow of our Entity Relation Diagram to simplify the work flow.
![moviedb1](https://github.com/ujjawalyt/Movie-Booking-Api/assets/87421981/614d7cc4-380a-44f0-8943-21a46d1c5931)


### Prerequisites
- Java Development Kit (JDK) should be installed on your machine.
- MySQL should be installed and running.


### Installation & Run
- Before running the API server, you have to update the database configuration inside the application.properties file
- Update the port number, username and password as per your local database configuration
````
    server.port=8888

    spring.datasource.url=jdbc:mysql://localhost:3306/ecomdb;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username= *****                               (put your username)
    spring.datasource.password= *****                               (put your password)   
    
````
## API Root Endpoint

`https://localhost:8888/index.html`

`http://localhost:8888/swagger-ui.html`


###  Authentication
Authentication is required for certain endpoints. It is implemented using JWT (JSON Web Tokens).
To access authenticated endpoints, include the JWT token in the Authorization header of the request:

`Authorization: Bearer {token}`
