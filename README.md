# Library Management System

> A modern web-based library management system with Java Spring Boot backend and responsive frontend

## 📋 Project Overview

This is a **Library Management System** built with Java Spring Boot and MySQL. It features a modern web interface for managing book records, allowing users to search, add, update, and delete books through a responsive web UI.

**Key Highlights:**
- 🌐 Modern web interface with Bootstrap styling
- 🔍 Real-time search by title, author, or genre
- ➕ Add/edit books with form validation
- 📊 Dynamic table with availability status
- 🗑️ Delete books with confirmation
- 📱 Responsive design for all devices

---

## 🛠️ Prerequisites

| Requirement | Version | Description |
|-------------|---------|-------------|
| Java | 11+ | JDK for Spring Boot |
| Maven | 3.6+ | Build tool (or use wrapper) |
| MySQL | 5.7+ | Database server |
| Node.js | Optional | For frontend development |

## 🚀 Setup Instructions

### Step 1: Database Setup
1. Install MySQL Server if not already installed
2. Run the `database_setup.sql` script to create the database and table:
   ```sql
   CREATE DATABASE library_db;
   USE library_db;
   -- Run the rest of the script
   ```
3. Update database credentials in `src/main/resources/application.properties` if needed

### Step 2: Install Dependencies
- **Maven**: Download from [maven.apache.org](https://maven.apache.org/download.cgi) and add to PATH
- **JDBC Driver**: Automatically downloaded via Maven (mysql-connector-java)

### Step 3: Run the Application

#### Option 1: Using Maven (Recommended)
```bash
# Compile and run
mvn spring-boot:run
```

#### Option 2: Manual Compilation (if Maven unavailable)
```bash
# Download Spring Boot JARs manually (complex)
# Or install Maven
```

### Step 4: Access the Application
- **Web UI**: Open `http://localhost:8080` in your browser
- The homepage serves the modern web interface

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/library/
│   │   ├── Main.java              # Spring Boot application
│   │   ├── BookController.java    # REST API endpoints
│   │   ├── LibraryDAO.java        # Data access layer
│   │   ├── Book.java              # Entity model
│   │   ├── DatabaseConnection.java # DB connection
│   │   └── ExportUtility.java     # CSV export
│   └── resources/
│       ├── application.properties # DB config
│       └── static/
│           └── index.html         # Web UI
```

---

## 🔧 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/search?title=X&author=Y&genre=Z` | Search books |
| POST | `/api/books` | Add new book |
| PUT | `/api/books` | Update book |
| DELETE | `/api/books/{id}` | Delete book |

---

## 🎨 Features

### Web Interface
- **Search Bar**: Filter books by title, author, or genre
- **Add/Edit Form**: Input validation and auto-clear
- **Books Table**: Sortable table with status badges
- **Actions**: Edit and delete buttons per row
- **Responsive**: Works on desktop and mobile

### Backend
- **Spring Boot**: RESTful API with JPA
- **MySQL Integration**: Persistent storage
- **Error Handling**: Proper exception management

---

## 🐛 Troubleshooting

### Common Issues
1. **Port 8080 occupied**: Change port in `application.properties`
2. **Database connection failed**: Check MySQL credentials
3. **JDBC driver missing**: Ensure Maven downloads dependencies
4. **Compilation errors**: Use Java 11+ and Maven 3.6+

### Database Issues
- Ensure MySQL service is running
- Verify `library_db` database exists
- Check user permissions

---

## 📝 Development

### Adding New Features
1. Update `BookController.java` for new endpoints
2. Modify `index.html` for UI changes
3. Add methods to `LibraryDAO.java` for data operations

### Building for Production
```bash
mvn clean package
java -jar target/library-management-1.0-SNAPSHOT.jar
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit changes
4. Push to GitHub
5. Create a Pull Request

---

## 📄 License

This project is open-source. Feel free to use and modify.