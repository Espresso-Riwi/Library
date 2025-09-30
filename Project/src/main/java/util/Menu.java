package utils;

import javax.swing.JOptionPane;
import dao.UsersDAO;
import implementation.UsersDAOIMP;
import model.User;
import java.util.List;

public class Menu {

    private static final UsersDAO usersDAO = new UsersDAOIMP();

    public static void showMainMenu() {
        boolean running = true;

        while (running) {
            String menu = """
                    --- Main Menu ---
                    1. Users
                    2. Materials
                    3. Loans
                    4. Exit
                    """;

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) break; // Cancel closes menu

            switch (input) {
                case "1" -> showUserMenu();
                case "2" -> showMaterialMenu();
                case "3" -> showLoanMenu();
                case "4" -> running = false;
                default -> JOptionPane.showMessageDialog(null, "Invalid option");
            }
        }
    }

    private static void showUserMenu() {
        String menu = """
                --- User Management ---
                1. Register User
                2. Find User by ID
                3. List Users
                4. Back
                """;

        String input = JOptionPane.showInputDialog(menu);
        if (input == null) return;

        switch (input) {
            case "1" -> {
                String dni = JOptionPane.showInputDialog("Enter DNI:");
                String name = JOptionPane.showInputDialog("Enter Name:");
                String email = JOptionPane.showInputDialog("Enter Email:");
                String password = JOptionPane.showInputDialog("Enter Password:");
                if (dni != null && name != null && email != null && password != null) {
                    usersDAO.save(new User(dni, name, email, password));
                    JOptionPane.showMessageDialog(null, "✅ User saved successfully!");
                }
            }
            case "2" -> {
                String idStr = JOptionPane.showInputDialog("Enter User ID:");
                if (idStr != null) {
                    try {
                        int id = Integer.parseInt(idStr);
                        User user = usersDAO.getById(id);
                        if (user != null) {
                            JOptionPane.showMessageDialog(null,
                                    "ID: " + user.getId() + "\nDNI: " + user.getDni() +
                                            "\nName: " + user.getName() +
                                            "\nEmail: " + user.getEmail());
                        } else {
                            JOptionPane.showMessageDialog(null, "⚠️ User not found");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid ID");
                    }
                }
            }
            case "3" -> {
                List<User> users = usersDAO.getAll();
                if (users.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "⚠️ No users registered");
                } else {
                    StringBuilder sb = new StringBuilder("📋 Users:\n");
                    for (User u : users) {
                        sb.append("ID: ").append(u.getId())
                          .append(" | Name: ").append(u.getName())
                          .append(" | Email: ").append(u.getEmail())
                          .append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
            }
            case "4" -> {} // Back
            default -> JOptionPane.showMessageDialog(null, "Invalid option");
        }
    }

    private static void showMaterialMenu() {
        String menu = """
                --- Material Management ---
                1. Register Book
                2. Register Magazine
                3. Find Material by ID
                4. List Materials
                5. Back
                """;

        String input = JOptionPane.showInputDialog(menu);
        if (input == null) return;

        switch (input) {
            case "1" -> JOptionPane.showMessageDialog(null, "Register Book selected");
            case "2" -> JOptionPane.showMessageDialog(null, "Register Magazine selected");
            case "3" -> JOptionPane.showMessageDialog(null, "Find Material by ID selected");
            case "4" -> JOptionPane.showMessageDialog(null, "List Materials selected");
            case "5" -> {}
            default -> JOptionPane.showMessageDialog(null, "Invalid option");
        }
    }

    private static void showLoanMenu() {
        String menu = """
                --- Loan Management ---
                1. Register Loan
                2. Register Return
                3. Find Loans by User
                4. List Active Loans
                5. Back
                """;

        String input = JOptionPane.showInputDialog(menu);
        if (input == null) return;

        switch (input) {
            case "1" -> JOptionPane.showMessageDialog(null, "Register Loan selected");
            case "2" -> JOptionPane.showMessageDialog(null, "Register Return selected");
            case "3" -> JOptionPane.showMessageDialog(null, "Find Loans by User selected");
            case "4" -> JOptionPane.showMessageDialog(null, "List Active Loans selected");
            case "5" -> {}
            default -> JOptionPane.showMessageDialog(null, "Invalid option");
        }
    }
}
