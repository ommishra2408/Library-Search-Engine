import java.sql.*;
import java.util.*;

public class LibraryDAO {

    // ADD BOOK
    public void addBook(Book book) {
        String sql = "INSERT INTO books000001(title, author, genre, isbn) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getGenre());
            ps.setString(4, book.getIsbn());

            ps.executeUpdate();
            System.out.println("Book Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ALL BOOKS
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books000001";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getString("isbn")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // SEARCH BOOK
    public List<Book> searchBooks(String keyword) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books000001 WHERE title LIKE ? OR author LIKE ? OR genre LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String key = "%" + keyword + "%";

            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getString("isbn")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // DELETE BOOK
    public void deleteBook(int id) {
        String sql = "DELETE FROM books000001 WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Book Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE BOOK
    public void updateBook(Book book) {
        String sql = "UPDATE books000001 SET title=?, author=?, genre=?, isbn=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getGenre());
            ps.setString(4, book.getIsbn());
            ps.setInt(5, book.getId());

            ps.executeUpdate();
            System.out.println("Book Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}