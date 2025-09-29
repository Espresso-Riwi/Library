
public class Magazine extends Item {
    private int issueNumber;
    private String topic;

    public Magazine(int id, String title, String author, boolean available, int issueNumber, String topic) {
        super(id, title, author, available, "MAGAZINE");
        setIssueNumber(issueNumber);
        setTopic(topic);

    }

    public int getIssueNumber() { return issueNumber; }

    public String getTopic() {
        return topic;
    }

    public void setIssueNumber(int issueNumber) { this.issueNumber = issueNumber; }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                ", type='" + type + '\'' +
                ", issueNumber=" + issueNumber +
                '}';
    }
}