import java.util.List;

public class Main {
    public static void main(String[] args) {
        UsersDAOIMP u = new UsersDAOIMP();
        List<User> userList = u.getAll();

        for (User user : userList){
            System.out.println("ID:"+user.getDni()+"\t\tName: "+user.getName());
        }
    }
}
