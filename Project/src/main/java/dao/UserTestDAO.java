public interface UserTestDAO {
    void addUser(User user);
    User getUserByDni(String dni);
    void updateUser(User user);
}
