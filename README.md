# 📚 Library Search Engine

A Java-based Library Management and Search System built using **JDBC**, **MySQL**, and **Swing GUI**.
This application allows users to add, search, and view books in a structured and user-friendly interface.

---

## 🚀 Features

* 🔍 Search books by title, author, or genre
* ➕ Add new books to the database
* 📖 View all available books
* 🖥️ Interactive desktop GUI using Java Swing
* 🗄️ MySQL database integration using JDBC

---

## 🛠️ Tech Stack

* **Java (JDK 8+)**
* **JDBC (Java Database Connectivity)**
* **MySQL**
* **Swing (GUI Framework)**

---

## 📂 Project Structure

```
Library-Search-Engine/
│
├── src/                # Java source files
│   ├── Main.java
│   ├── LibraryDAO.java
│   ├── LibraryUI.java
│
├── lib/                # External libraries (MySQL Connector)
│   └── mysql-connector-j-9.7.0.jar
│
└── README.md
```

---

## ⚙️ Setup Instructions

### 1. Clone the Repository

```
git clone https://github.com/ommishra2408/Library-Search-Engine.git
cd Library-Search-Engine/src
```

---

### 2. Setup MySQL Database

Open MySQL and run:

```
CREATE DATABASE library_db00001;
USE library_db00001;

CREATE TABLE books1 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    genre VARCHAR(50),
    isbn VARCHAR(20)
);
```

---

### 3. Compile the Project

```
javac -cp ".;../lib/mysql-connector-j-9.7.0.jar" *.java
```

---

### 4. Run the Application

```
java -cp ".;../lib/mysql-connector-j-9.7.0.jar" Main
```

---

## ⚠️ Important Notes

* Ensure MySQL server is running before starting the application
* Database name and table name must match the configuration in your Java code
* Update database credentials (username/password) in `LibraryDAO.java` if needed

---

## 🧪 Sample Data (Optional)

```
INSERT INTO books1 (title, author, genre, isbn) VALUES
('The Alchemist', 'Paulo Coelho', 'Fiction', '1234567890'),
('Clean Code', 'Robert C. Martin', 'Programming', '9780132350884');
```

---

## 📌 Future Improvements

* 🌐 Convert to a web-based application using Spring Boot
* 🔐 Add user authentication system
* 📊 Advanced filtering and sorting
* ☁️ Deploy online for public access

---

## 👨‍💻 Author

**Om Mishra**
B.Tech AI & Data Science
Chandigarh Engineering College

---

## 📜 License

This project is for educational purposes.
