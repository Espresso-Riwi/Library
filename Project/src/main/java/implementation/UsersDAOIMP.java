import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAOIMP {

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try(Connection connection = DBConnection.getConnection();

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)){

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                users.add(new User(id, name, email, password));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
}
