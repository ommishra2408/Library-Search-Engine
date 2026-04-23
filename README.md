# Library Search Engine

> A Java-based library management system with MySQL database integration

## 📋 Project Overview

This is a **Library Search Engine** application built with Java and MySQL. It provides a command-line interface for managing book records in a library database, allowing users to search, add, update, and delete books.

**Key Highlights:**
- 🔍 Search books by title, author, genre, or year
- ➕ Add new books to the database
- ✏️ Update existing book details
- 🗑️ Delete books from the system
- ✅ Check book availability

---

## 🛠️ Prerequisites

| Requirement | Version | Description |java -cp "src/main/java;
|-------------|---------|-------------|
| Java | 8+ | JDK for compiling and running |
| MySQL | 5.7+ | Database server |
| JDBC Driver | 8.0+ | MySQL Connector/J |java -cp "src/main/java;mysql-connector-java-8.0.33.jar" library.Main

## 🚀 Setup Instructions

### Step 1: Database Setup
1. Install MySQL Server if not already installed
2. Run the `database_setup.sql` script to create the database and table
3. Update credentials in `DatabaseConnection.java` if needed

### Step 2: JDBC Driver
- The project already includes `mysql-connector-j-9.7.0.jar`
- If needed, download from [MySQL website](https://dev.mysql.com/downloads/connector/j/)

### Step 3: Compile & Run

> ⚠️ **Important:** Always compile from `src/main/java` and run from project root

```powershell
# Compile
cd "c:\Users\HP\Desktop\New folder\src\main\java"
javac library/*.java

# Run
cd "c:\Users\HP\Desktop\New folder"
java -cp "src\main\java;mysql-connector-j-9.7.0.jar" library.Main
```

---

## 📊 Database Schema

| Field | Type | Description |
|-------|------|-------------|
| book_id | INT (PK) | Unique identifier |
| title | VARCHAR(100) | Book title |
| author | VARCHAR(100) | Author name |
| genre | VARCHAR(50) | Book genre |
| year | INT | Publication year |
| availability | BOOLEAN | Available/Checked out |

**Database:** `library_db`  
**Table:** `books`

---

## 📁 Project Structure

```
New folder/
├── README.md                 # Project documentation
├── database_setup.sql        # Database schema
├── temp_setup.sql           # Temporary setup script
├── mysql-connector-j-9.7.0.jar  # JDBC Driver
└── src/main/java/library/
    ├── Book.java            # Book model class
    ├── DatabaseConnection.java  # DB connection manager
    ├── LibraryDAO.java      # Data access layer
    └── Main.java            # Application entry point
```

---

## 💻 Technology Stack

- **Language:** Java
- **Database:** MySQL
- **JDBC:** MySQL Connector/J
- **Architecture:** DAO (Data Access Object) Pattern