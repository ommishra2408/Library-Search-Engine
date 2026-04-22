-- Create Database
CREATE DATABASE IF NOT EXISTS library_db;

-- Use Database
USE library_db;

-- Drop table if exists to ensure correct schema
DROP TABLE IF EXISTS books;

-- Create Table
CREATE TABLE books (
    book_id INT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    year INT,
    availability BOOLEAN DEFAULT TRUE
);

-- Insert Sample Data
INSERT INTO books (book_id, title, author, genre, year, availability) VALUES
(1, 'Java Programming', 'John Doe', 'Programming', 2020, TRUE),
(2, 'Data Structures', 'Jane Smith', 'Computer Science', 2019, FALSE),
(3, 'Database Systems', 'Bob Johnson', 'Database', 2021, TRUE);