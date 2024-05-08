import java.util.ArrayList;

public class Author {
    private Profile profile = new Profile();
    private ArrayList<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(Profile profile, ArrayList<Book> books) {
        this.profile = profile;
        this.books = books;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
