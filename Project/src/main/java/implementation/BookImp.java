import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookImp implements BookDAO {

    @Override
    public void addBook(Book book) {
        String sqlItem = "INSERT INTO item (id, title, author, available, type) VALUES (?, ?, ?, ?, ?)";
        String sqlBook = "INSERT INTO book (id, isbn, publisher) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
                 PreparedStatement stmtBook = conn.prepareStatement(sqlBook)) {

                stmtItem.setInt(1, book.getId());
                stmtItem.setString(2, book.getTitle());
                stmtItem.setString(3, book.getAuthor());
                stmtItem.setBoolean(4, book.isAvailable());
                stmtItem.setString(5, "BOOK");
                stmtItem.executeUpdate();

                stmtBook.setInt(1, book.getId());
                stmtBook.setString(2, book.getIsbn());
                stmtBook.setString(3, book.getPublisher());
                stmtBook.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getBookById(int id) {
        String sql = "SELECT i.id, i.title, i.author, i.available, b.isbn, b.publisher " +
                "FROM item i JOIN book b ON i.id = b.id WHERE i.id = ? AND i.type = 'BOOK'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available"),
                        rs.getString("isbn"),
                        rs.getString("publisher")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT i.id, i.title, i.author, i.year, i.available, b.isbn, b.publisher " +
                "FROM item i JOIN book b ON i.id = b.id WHERE i.type = 'BOOK'";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available"),
                        rs.getString("isbn"),
                        rs.getString("publisher")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateBook(Book book) {
        String sqlItem = "UPDATE item SET title = ?, author = ?, available = ?, type = ? WHERE id = ?";
        String sqlBook = "UPDATE book SET isbn = ?, publisher = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
                 PreparedStatement stmtBook = conn.prepareStatement(sqlBook)) {

                stmtItem.setString(1, book.getTitle());
                stmtItem.setString(2, book.getAuthor());
                stmtItem.setBoolean(3, book.isAvailable());
                stmtItem.setString(4, "BOOK");
                stmtItem.setInt(5, book.getId());
                stmtItem.executeUpdate();

                stmtBook.setString(1, book.getIsbn());
                stmtBook.setString(2, book.getPublisher());
                stmtBook.setInt(3, book.getId());
                stmtBook.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int id) {
        String sqlBook = "DELETE FROM book WHERE id = ?";
        String sqlItem = "DELETE FROM item WHERE id = ? AND type = 'BOOK'";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtBook = conn.prepareStatement(sqlBook);
                 PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {

                stmtBook.setInt(1, id);
                stmtBook.executeUpdate();

                stmtItem.setInt(1, id);
                stmtItem.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}