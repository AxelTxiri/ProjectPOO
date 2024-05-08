import java.util.ArrayList;

public class Client extends User{
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Client() {
    }

    public Client(Profile profile, String username, String password, ArrayList<Book> borrowedBooks) {
        super(profile, username, password);
        this.borrowedBooks = borrowedBooks;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
