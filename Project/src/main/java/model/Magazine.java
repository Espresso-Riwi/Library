
public class Magazine extends Item {
    private int issue_number;
    private String topic;

    public Magazine(int id, String title, String author, boolean available, int issue_number, String topic) {
        super(id, title, author, available, "MAGAZINE");
        setIssueNumber(issue_number);
        setTopic(topic);
    }

    public Magazine(String title, String author, int issue_number, String topic) {
        super(title, author, true, "MAGAZINE");
        setIssueNumber(issue_number);
        setTopic(topic);
    }

    public int getIssueNumber() { return issue_number; }

    public String getTopic() {
        return topic;
    }

    public void setIssueNumber(int issueNumber) { this.issue_number = issueNumber; }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "ID: "+id+"\nTitle: "+title+"\nAuthor: "+author+"\nAvailable: "+available+"\nIssue Number: "+issue_number+"\nTopic: "+topic;
    }
}