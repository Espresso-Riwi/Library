public abstract class Item {
    protected int id;
    protected String title;
    protected String author;
    protected int year;
    protected boolean available;
    protected String type; // BOOK | MAGAZINE

    public Item(int id, String title, String author, int year, boolean available, String type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = available;
        this.type = type;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isAvailable() { return available; }
    public String getType() { return type; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(int year) { this.year = year; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setType(String type) { this.type = type; }
}