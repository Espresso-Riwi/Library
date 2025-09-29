public abstract class Item {
    protected int id;
    protected String title;
    protected String author;
    protected boolean available;
    protected String type;

    public Item(int id, String title, String author, boolean available, String type) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setAvailable(available);
        setType(type);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public String getType() { return type; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setType(String type) { this.type = type; }
}