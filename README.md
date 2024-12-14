# Book_Management_System

## Description
The BookManagement System is a simple Java Swing application that allows users to manage books in a library. It provides functionality to add, update, delete, and search for books. This application uses a MySQL database to store book information.

## Features
- Add new books
- Update existing book information
- Delete books
- Search for books by ID
- Clear input fields

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL database
- JDBC MySQL Connector

## Setup

### Database Setup
1. Install MySQL and create a database named `library`.
2. Create a table `books` with the following schema:
   sql Queries
   CREATE TABLE books (
       book_id INT PRIMARY KEY,
       book_name VARCHAR(255),
       book_author VARCHAR(255),
       book_price DOUBLE
   );
