# Users-Microservice
A simple Users Microservice

# THE PROBLEM
This is a small Users Microservice written in Java.
	The service allows to Add a user, remove, update, list all users, or filter users by country.
	For the details about the Microservice please refer to the section SWAGGERS and Endpoints Discovery
Key concepts while taking decisions of implementation : KISS (Keep It Simple and Stupid) - for simplicity, e.g. it was decided to Use SpringBoot because it is very simple, CrudRepository<T, Long> class for mocking the DB and testing the CRUD methods.
While implementing the algorithmic of a Java program (including Microservices) the main guide are SOLID principles and OOP. The Observer Pattern is also applied within the Notifications.


# RUNNING the APPLICATION
1. Using Eclipse:
	import the project as a Maven project.
	Run as Java Application the App.java from the package com.viktor

2. Using Maven
	Please make sure you have maven installed.
	Enter in console in the application root where pom.xml file is and run the command : mvn clean install
	Run the application by going inside /target package from console and run the command : java -jar users-microservice-0.0.1-SNAPSHOT.jar


# SWAGGER and Endpoints Discovery
can be accessed over this link while running the application : http://localhost:8080/swagger-ui.html

# QUERYING with POSTMAN
	List all users: 
		Call a GET method URI : http://localhost:8080/users the answer will be a JSON  
	Find a user: 
		Assuming you have a collection of users set in memory, call GET method URI : http://localhost:8080/users/user/1 Where 1 is the user ID (you can change it)
	Delete a user:
		Call DELETE method URI : http://localhost:8080/users/delete/1   where 1 is the user ID you want to delete.
	Adding a user:
		call POST method URI : http://localhost:8080/users/save where in the POSTMAN body insert your JSON like {"id": 5, "firstName":"Diana","lastName":"Angelo","nickName":"dangelo","password":"pass","email":"d.a@gmail.com","country":"uk"} - for an existing user with the ID = 5 or {"firstName":"Diana","lastName":"Angelo","nickName":"dangelo","password":"pass","email":"d.a@gmail.com","country":"uk"} for a new User, the ID generates automatically.

# CONSOLE and LOGS
	You have a trace of the actions in the console and the Logs, including the notifications over a saved user (a new or an existing one).
