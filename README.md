# WebServiceCrud

# Project name
WebService with CRUD

## Description
This is a WebService application with CRUD function that is connected with MYSQLdatabase to store books.
To work with the CRUD function you need to use postman.
The function of adding and getting books is open for all. But to update och delete books you have to be a registered
user. To be a register user you need to use the register url. You then need to login with login url. This will give you
a token that you need to use to get access to update and delete url.

# Url for postman:
## Add book
Post method
http://localhost:8080/auth/books
## Get all books
Get method
http://localhost:8080/auth/books
## Get on book with ID
Get method
http://localhost:8080/auth/books/{id}
## Register user
Post method
http://localhost:8080/auth/register
## Login with user
Post method
http://localhost:8080/auth/login
## Update book with book ID (need to be a user)
Put Method 
http://localhost:8080/user/{id}
## Delete book with book ID (need to be a user)
Delete method
http://localhost:8080/user/{id}

The payload need to be in json format for exemple
{
"title": "Pippi Långström",
"author": "Astrid Lindgren"
}

## Installation
### Javaversion: 19 java version 20.0.1
### Download and install MySQL Workbench
### Download and install Postman
### Download and install IntelliJ
### Clone the project on GitHub and open it in IntelliJ.
### Run the program from the Main class and the menu will start working.

## License
MIT License
