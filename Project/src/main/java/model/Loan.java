import java.time.LocalDate;

public class Loan {
    private int id;
    private int userId;
    private int itemId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean returned;

    public Loan(int id, int userId, int itemId, LocalDate loanDate, LocalDate returnDate, boolean returned) {
        setId(id);
        setUserId(userId);
        setItemId(itemId);
        setLoanDate(loanDate);
        setReturnDate(returnDate);
        setReturned(returned);
    }

    // Getters & Setters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getItemId() { return itemId; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isReturned() { return returned; }

    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setReturned(boolean returned) { this.returned = returned; }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", loanDate=" + loanDate +
                ", returnDate=" + (returnDate != null ? returnDate.toString() : "null") +
                ", returned=" + returned +
                '}';
    }
}