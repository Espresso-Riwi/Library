import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanImp implements LoanDAO {

    @Override
    public void addLoan(Loan loan) {
        String sql = "INSERT INTO loan (id, user_id, item_id, loan_date, return_date, returned) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loan.getId());
            stmt.setInt(2, loan.getUserId());
            stmt.setInt(3, loan.getItemId());
            stmt.setDate(4, Date.valueOf(loan.getLoanDate()));
            if (loan.getReturnDate() != null) {
                stmt.setDate(5, Date.valueOf(loan.getReturnDate()));
            } else {
                stmt.setNull(5, Types.DATE);
            }
            stmt.setBoolean(6, loan.isReturned());

            stmt.executeUpdate();
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
                return mapResultSetToLoan(rs);
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
                loans.add(mapResultSetToLoan(rs));
            }

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
    public void markAsReturned(int id) {
        String sql = "UPDATE loan SET returned = TRUE, return_date = ? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Loan mapResultSetToLoan(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int userId = rs.getInt("user_id");
        int itemId = rs.getInt("item_id");
        LocalDate loanDate = rs.getDate("loan_date").toLocalDate();
        Date returnDateSql = rs.getDate("return_date");
        LocalDate returnDate = (returnDateSql != null) ? returnDateSql.toLocalDate() : null;
        boolean returned = rs.getBoolean("returned");

        return new Loan(id, userId, itemId, loanDate, returnDate, returned);
    }
}