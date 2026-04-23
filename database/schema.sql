-- Create Database
CREATE DATABASE library_db01;

-- Use Database
USE library_db01;

-- Create Table
CREATE TABLE Books1 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    genre VARCHAR(50),
    isbn VARCHAR(20)
);