# Library Search Engine

A Java application for managing a library database using JDBC and MySQL.

## Prerequisites

- Java 8 or higher
- MySQL Server
- MySQL JDBC Driver (mysql-connector-java.jar)

## Setup

1. **Install MySQL and create database:**
   - Install MySQL Server if not already installed.
   - Run the `database_setup.sql` script to create the database and table.
   - Update the database credentials in `DatabaseConnection.java` if necessary.

2. **Add JDBC Driver:**
   - Download `mysql-connector-java.jar` from MySQL website.
   - Add it to your classpath when compiling and running the application.

## How to Compile and Run

**Important:** Always compile from the `src/main/java` directory and run from the project root.

### Compile:
```bash
cd "c:\Users\HP\Desktop\New folder\src\main\java"
javac library/*.java
```

### Run:
```bash
cd "c:\Users\HP\Desktop\New folder"
java -cp "src\main\java;mysql-connector-j-9.7.0.jar" library.Main
```

**Wrong way (don't do this):**
```bash
cd "c:\Users\HP\Desktop\New folder\src\main\java\library"
javac Main.java  # This fails because of dependencies
java Main        # This fails because of classpath and package
```

## Features

- Search books by title, author, genre, or year
- Add new books
- View all books
- Update book details
- Delete books
- Check book availability

## Database Schema

- Database: library_db
- Table: books
  - book_id (INT, Primary Key)
  - title (VARCHAR(100))
  - author (VARCHAR(100))
  - genre (VARCHAR(50))
  - year (INT)
  - availability (BOOLEAN)