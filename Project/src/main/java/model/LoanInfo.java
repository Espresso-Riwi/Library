public class LoanInfo {
    private String title;
    private String author;
    private int user_id;
    private String type;
    private int loanId;

    public LoanInfo(String title, String author, int user_id, String type) {
        this.title = title;
        this.author = author;
        this.user_id = user_id;
        this.type = type;
    }

    public LoanInfo(String title, String author, int user_id, String type, int loanId) {
        this.title = title;
        this.author = author;
        this.user_id = user_id;
        this.type = type;
        this.loanId = loanId;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Title: "+title+"\nAuthor: "+author+"\nType: "+type;
    }
}
