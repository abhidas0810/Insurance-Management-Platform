# Insurance-Management-Platform
<p align="center">
  <img src="https://www.linkpicture.com/q/Insurance-Management-Platform-Logo.png"/>
</p>

## About

The project is a Spring Boot-based RESTful API for an insurance management system. It uses Spring Data JPA for persistence and an embedded database for development purposes.

The system has three main domain models: Client, InsurancePolicy, and Claim. A Client represents a person who has one or more insurance policies, and an InsurancePolicy represents the coverage details of an insurance policy, including its associated client. A Claim represents an insurance claim made by a client against one of their policies.

## Tech Stack

**Client:** Java, SpringBoot, Spring Data JPA, Hibernate, JUnit and Mockito

**RDBMS:** MySQL

**Testing:** Swagger


### Modules
- Client Module
- Insurance-Policy Module
-	Claim Module
-	Login Module

##  Entity Relationship

![Travel Management System (ER Diagram)](https://www.linkpicture.com/q/Insurance-Management-Platform-ER-Diagram_2.jpeg)

## Run Locally

Clone the project

```bash
  git clone https://github.com/abhidas0810/Insurance-Management-Platform.git
```

Go to the project resources

-  src/main/resources and modify the MySQL login details in the application.properties file to match the system on which the application will be executed.


Run the main file

- src\main\java\com\insuranceManagementPlatform\InsuranceManagementPlatformApplication.java 

Start the server

 - http://localhost:8888/swagger-ui/index.html#/


## API Module Endpoints

### Client Module

* POST- http://localhost:8888/api/clients : Register client details.
* GET- http://localhost:8888/api/clients : Get all client details.
* GET - http://localhost:8888/api/clients/id : Get details of specific client using emailId.
* PUT - http://localhost:8888/api/clients/id : Update the details of specific client using emailId.
* DELETE - http://localhost:8888/api/clients/id : Delete the details of specific client using emailId.

### Insurance_Policy Module

* POST- http://localhost:8888/api/policies : Create policy details.
* GET- http://localhost:8888/api/policies : Get all policies details.
* GET - http://localhost:8888/api/policies/id : Get details of specific policy using policyNumber.
* PUT - http://localhost:8888/api/policies/id : Update the details of specific policy using policyNumber.
* DELETE - http://localhost:8888/api/policies/id : Delete the details of specific policy using policyNumber.

### Claim Module

* POST- http://localhost:8888/api/claims : Create claim details.
* GET- http://localhost:8888/api/claims : Get all claims details.
* GET - http://localhost:8888/api/claims/id : Get details of specific claim using claimNumber.
* PUT - http://localhost:8888/api/claims/id : Update the details of specific claim using claimNumber.
* DELETE - http://localhost:8888/api/claims/id : Delete the details of specific claim using claimNumber.

<!-- ![Logo](https://i.postimg.cc/kM0tpJKd/p2.png) -->

## Author
- [Abhishek Das](https://github.com/abhidas0810)
