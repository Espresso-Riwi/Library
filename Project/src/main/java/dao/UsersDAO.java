package dao;

import java.util.List;
import model.User;

public interface UsersDAO {
    List<User> getAll();
    User getById(int id);
    void save(User user);

}
