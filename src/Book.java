import java.util.Date;

public class Book {
    private String isbn;
    private String title;
    private Author author;
    private Date published;
    private boolean isAvailable;

    public Book() {
    }
    public Book(String isbn, String title, Author author, Date published, boolean isAvailable) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.published = published;
        this.isAvailable = isAvailable;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
