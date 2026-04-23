package library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {
    public void addBook(Book book) {
        String sql = "INSERT INTO books (book_id, title, author, genre, year, availability) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getGenre());
            stmt.setInt(5, book.getYear());
            stmt.setBoolean(6, book.isAvailability());
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getBoolean("availability")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving books: " + e.getMessage());
        }
        return books;
    }

    public void addSampleBooks() {
        if (getAllBooks().isEmpty()) {
            addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925, true));
            addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee", "Fiction", 1960, true));
            addBook(new Book(3, "1984", "George Orwell", "Dystopian", 1949, false));
        }
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title=?, author=?, genre=?, year=?, availability=? WHERE book_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setInt(4, book.getYear());
            stmt.setBoolean(5, book.isAvailability());
            stmt.setInt(6, book.getBookId());
            stmt.executeUpdate();
            System.out.println("Book updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }

    public void deleteBook(int bookId) {
        String sql = "DELETE FROM books WHERE book_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
            System.out.println("Book deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }

    public List<Book> searchBooks(String title, String author, String genre, Integer year) {
        List<Book> books = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM books WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (title != null && !title.trim().isEmpty()) {
            sql.append(" AND title LIKE ?");
            params.add("%" + title + "%");
        }
        if (author != null && !author.trim().isEmpty()) {
            sql.append(" AND author LIKE ?");
            params.add("%" + author + "%");
        }
        if (genre != null && !genre.trim().isEmpty()) {
            sql.append(" AND genre LIKE ?");
            params.add("%" + genre + "%");
        }
        if (year != null) {
            sql.append(" AND year = ?");
            params.add(year);
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getBoolean("availability")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Search error: " + e.getMessage());
        }
        return books;
    }

    public boolean checkAvailability(int bookId) {
        String sql = "SELECT availability FROM books WHERE book_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("availability");
            }
        } catch (SQLException e) {
            System.err.println("Availability check error: " + e.getMessage());
        }
        return false;
    }
}