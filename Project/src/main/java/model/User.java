package model;
public class User {
    private int id;
    private String dni;
    private String name;
    private String email;
    private String password;

    public User(String dni, String name, String email, String password) {
        setName(name);
        setEmail(email);
        setDni(dni);
        setPassword(password);
    }

    public User(int id, String dni, String name, String email, String password) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
