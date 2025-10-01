import java.util.List;

public interface LoanDAO {
    void addLoan(Loan loan);
    Loan getLoanById(int id);
    List<Loan> getAllLoans();
    void updateLoan(Loan loan);
    void deleteLoan(int id);
    List<LoanInfo> userLoans(User user);
    void markAsReturned(int id);
    LoanInfo getLoanByItemAndUserId(User user, String title, String type);
}