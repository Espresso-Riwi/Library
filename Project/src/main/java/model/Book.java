public class Book extends Item {
    private String isbn;
    private String publisher;

    public Book(int id, String title, String author, int year, boolean available, String isbn, String publisher) {
        super(id, title, author, year, available, "BOOK");
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", available=" + available +
                ", type='" + type + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';


    }
}