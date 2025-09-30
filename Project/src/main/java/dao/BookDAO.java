import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    Book getBookById(int id);
    Book getBookByName(String name);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(int id);
}