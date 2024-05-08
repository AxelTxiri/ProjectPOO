import java.util.ArrayList;
import java.util.Date;

public class BookRepository {
    public static ArrayList<Book> allBooks = new ArrayList<Book>();

    public void createBook(String isbn, String title, Author author, Date publishDate, boolean isAvailable) {
        Book book = new Book(isbn, title, author, publishDate, isAvailable);
        allBooks.add(book);
        System.out.println("Book successfully created.");
        System.out.println();
    }
}
