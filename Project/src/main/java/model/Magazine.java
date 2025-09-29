
public class Magazine extends Item {
    private int issueNumber;
    private String publicationMonth;

    public Magazine(int id, String title, String author, boolean available, int issueNumber, String publicationMonth) {
        super(id, title, author, available, "MAGAZINE");
        this.issueNumber = issueNumber;
        this.publicationMonth = publicationMonth;
    }

    public int getIssueNumber() { return issueNumber; }
    public String getPublicationMonth() { return publicationMonth; }

    public void setIssueNumber(int issueNumber) { this.issueNumber = issueNumber; }
    public void setPublicationMonth(String publicationMonth) { this.publicationMonth = publicationMonth; }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                ", type='" + type + '\'' +
                ", issueNumber=" + issueNumber +
                ", publicationMonth='" + publicationMonth + '\'' +
                '}';
    }
}