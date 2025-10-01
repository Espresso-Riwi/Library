import java.sql.*;

public class UsersTestImp implements  UserTestDAO{

    @Override
    public void addUser(User user) {
        String sqlUser = "INSERT INTO userTest (name, email, dni) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtUser = conn.prepareStatement(sqlUser)) {
                stmtUser.setString(1, user.getName());
                stmtUser.setString(2, user.getEmail());
                stmtUser.setString(3, user.getDni());
                stmtUser.executeUpdate();

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
    public User getUserByDni(String dni) {
        String sql = "SELECT * FROM userTest where dni = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("dni"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User user) {

    }
}
