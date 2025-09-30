package implementation;

import dao.UsersDAO;
import model.User;
import util.DBConnection; // asegúrate de poner el paquete correcto
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAOIMP implements UsersDAO {

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";

        try (Connection connection = DBConnection.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id"); 
                String dni = rs.getString("dni");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");

                users.add(new User(id, dni, name, email, password));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO user (dni, name, email, password) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, user.getDni());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String dni = rs.getString("dni");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return new User(id, dni, name, email, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
