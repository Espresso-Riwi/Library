public class Book extends Item {
    private String isbn;
    private String editorial;

    public Book(int id, String title, String author, boolean available, String isbn, String editorial) {
        super(id, title, author, available, "BOOK");
        setIsbn(isbn);
        setEditorial(editorial);

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                ", type='" + type + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}