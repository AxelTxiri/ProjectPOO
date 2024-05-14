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

    public void updateBook(int id, String isbnUpdate, String titleUpdate, Author authorUpdate, Date publishDateUpdate) {
        if (isbnUpdate == ""){
            isbnUpdate = allBooks.get(id).getIsbn();
        }
        if (titleUpdate == ""){
            titleUpdate = allBooks.get(id).getTitle();
        }
        Book book = new Book(isbnUpdate, titleUpdate, authorUpdate, publishDateUpdate, allBooks.get(id).isAvailable());
        allBooks.set(id, book);
        System.out.println("Book successfully updated.");
        System.out.println();
    }

    public void deleteBook(int id) {
        if (allBooks.get(id).isAvailable()) {
            allBooks.remove(id);
            System.out.println("Book successfully deleted.");
            System.out.println();
        } else {
            System.out.println("You are not allowed to delete this book. Return the book first.");
            System.out.println();
        }
    }
}
