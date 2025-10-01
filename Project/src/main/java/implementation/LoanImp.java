import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanImp implements LoanDAO {

    @Override
    public void addLoan(Loan loan) {
        String sql = "INSERT INTO loan (user_id, item_id, loan_date, date_of_return) VALUES (?, ?, ?, ?)";
        String sqlItem = "UPDATE item SET available = false WHERE id= ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {

            stmt.setInt(1, loan.getUserId());
            stmt.setInt(2, loan.getItemId());
            stmt.setDate(3, Date.valueOf(loan.getLoanDate()));
            stmt.setDate(4, Date.valueOf(loan.getDateOfReturn()));


            stmt.executeUpdate();

            stmtItem.setInt(1, loan.getItemId());
            stmtItem.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Loan getLoanById(int id) {
        String sql = "SELECT * FROM loan WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loan";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                loans.add(new Loan(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("item_id"),
                        rs.getDate("loan_date").toLocalDate(),
                        rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                        rs.getBoolean("returned"),
                        rs.getDate("date_of_return").toLocalDate()
                ));
            };

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public void updateLoan(Loan loan) {
        String sql = "UPDATE loan SET user_id=?, item_id=?, loan_date=?, return_date=?, returned=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loan.getUserId());
            stmt.setInt(2, loan.getItemId());
            stmt.setDate(3, Date.valueOf(loan.getLoanDate()));
            if (loan.getReturnDate() != null) {
                stmt.setDate(4, Date.valueOf(loan.getReturnDate()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.setBoolean(5, loan.isReturned());
            stmt.setInt(6, loan.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLoan(int id) {
        String sql = "DELETE FROM loan WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LoanInfo> userLoans(User user) {
        List<LoanInfo> loanInfoList = new ArrayList<>();
        String sql = "SELECT i.title, i.author, l.user_id, i.type from loan l\n" +
                "    JOIN userTest u ON l.user_id = u.id\n" +
                "    JOIN item i ON l.item_id = i.id\n" +
                "    where u.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                loanInfoList.add(new LoanInfo(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("user_id"),
                        rs.getString("type")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loanInfoList;
    }

    @Override
    public void markAsReturned(int id) {
        String sql = "UPDATE loan SET returned = true, return_date = ? WHERE id= ?";
        String sqlItem = "UPDATE item set available = true";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             Statement statement = conn.createStatement()) {

            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, id);
            stmt.executeUpdate();
            statement.executeUpdate(sqlItem);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LoanInfo getLoanByItemAndUserId(User user, String title, String type) {
        String sql = """
                SELECT i.title, i.author, l.user_id, i.type, l.id
                from loan l
                JOIN userTest u ON l.user_id = u.id
                JOIN item i ON l.item_id = i.id
                where u.id = ? AND i.title = ? AND i.type = ?;
               """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getId());
            stmt.setString(2, title);
            stmt.setString(3, type);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new LoanInfo(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("user_id"),
                        rs.getString("type"),
                        rs.getInt("id")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}