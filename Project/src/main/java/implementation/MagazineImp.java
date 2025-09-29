import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagazineImp implements MagazineDAO {

    @Override
    public void addMagazine(Magazine magazine) {
        String sqlItem = "INSERT INTO item (id, title, author, year, available, type) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlMagazine = "INSERT INTO magazine (id, issueNumber, publicationMonth) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
                 PreparedStatement stmtMagazine = conn.prepareStatement(sqlMagazine)) {

                stmtItem.setInt(1, magazine.getId());
                stmtItem.setString(2, magazine.getTitle());
                stmtItem.setString(3, magazine.getAuthor());
                stmtItem.setInt(4, magazine.getYear());
                stmtItem.setBoolean(5, magazine.isAvailable());
                stmtItem.setString(6, "MAGAZINE");
                stmtItem.executeUpdate();

                stmtMagazine.setInt(1, magazine.getId());
                stmtMagazine.setInt(2, magazine.getIssueNumber());
                stmtMagazine.setString(3, magazine.getPublicationMonth());
                stmtMagazine.executeUpdate();

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
    public Magazine getMagazineById(int id) {
        String sql = "SELECT i.id, i.title, i.author, i.year, i.available, m.issueNumber, m.publicationMonth " +
                "FROM item i JOIN magazine m ON i.id = m.id WHERE i.id = ? AND i.type = 'MAGAZINE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Magazine(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getBoolean("available"),
                        rs.getInt("issueNumber"),
                        rs.getString("publicationMonth")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Magazine> getAllMagazines() {
        List<Magazine> magazines = new ArrayList<>();
        String sql = "SELECT i.id, i.title, i.author, i.year, i.available, m.issueNumber, m.publicationMonth " +
                "FROM item i JOIN magazine m ON i.id = m.id WHERE i.type = 'MAGAZINE'";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                magazines.add(new Magazine(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getBoolean("available"),
                        rs.getInt("issueNumber"),
                        rs.getString("publicationMonth")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazines;
    }

    @Override
    public void updateMagazine(Magazine magazine) {
        String sqlItem = "UPDATE item SET title = ?, author = ?, year = ?, available = ?, type = ? WHERE id = ?";
        String sqlMagazine = "UPDATE magazine SET issueNumber = ?, publicationMonth = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
                 PreparedStatement stmtMagazine = conn.prepareStatement(sqlMagazine)) {

                stmtItem.setString(1, magazine.getTitle());
                stmtItem.setString(2, magazine.getAuthor());
                stmtItem.setInt(3, magazine.getYear());
                stmtItem.setBoolean(4, magazine.isAvailable());
                stmtItem.setString(5, "MAGAZINE");
                stmtItem.setInt(6, magazine.getId());
                stmtItem.executeUpdate();

                stmtMagazine.setInt(1, magazine.getIssueNumber());
                stmtMagazine.setString(2, magazine.getPublicationMonth());
                stmtMagazine.setInt(3, magazine.getId());
                stmtMagazine.executeUpdate();

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
    public void deleteMagazine(int id) {
        String sqlMagazine = "DELETE FROM magazine WHERE id = ?";
        String sqlItem = "DELETE FROM item WHERE id = ? AND type = 'MAGAZINE'";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtMagazine = conn.prepareStatement(sqlMagazine);
                 PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {

                stmtMagazine.setInt(1, id);
                stmtMagazine.executeUpdate();

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