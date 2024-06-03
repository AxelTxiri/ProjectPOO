import java.util.ArrayList;

public class Seeder {
    public void sow(){
        Profile profile = new Profile("axel","tairi", DateController.seederDate("01-11-2001"));
        ArrayList<Admin.Permissions> permissions = new ArrayList<>();
        permissions.add(Admin.Permissions.WRITE);
        permissions.add(Admin.Permissions.WRITE);
        permissions.add(Admin.Permissions.DELETE);
        User superAdmin = new Admin(profile, "admin", "admin", permissions, true);
        UserRepository.users.add(superAdmin);

        Profile profile1 = new Profile("Vane","Garnica", DateController.seederDate("06-01-2001"));
        ArrayList<Admin.Permissions> permissions1 = new ArrayList<>();
        permissions.add(Admin.Permissions.WRITE);
        permissions.add(Admin.Permissions.WRITE);
        permissions.add(Admin.Permissions.DELETE);
        User admin = new Admin(profile, "VaneG", "Vane&Axel", permissions1, false);
        UserRepository.users.add(admin);

        Profile profile2 = new Profile("Max", "Delgado", DateController.seederDate("07-11-2001"));
        ArrayList<Book> borrowedBooks = new ArrayList<>();
        User client = new Client(profile2, "aone", "maromas", borrowedBooks);
        UserRepository.users.add(client);

        Profile authorProfile1 = new Profile("Stephen", "King", DateController.seederDate("21-09-1947"));
        ArrayList<Book> books1 = new ArrayList<>();
        Author author = new Author(authorProfile1, books1);
        AuthorRepository.authors.add(author);
        Profile authorProfile2 = new Profile("James", "Clear", DateController.seederDate("12-03-1986"));
        ArrayList<Book> books2 = new ArrayList<>();
        Author author2 = new Author(authorProfile2, books2);
        AuthorRepository.authors.add(author2);
        Profile authorProfile3 = new Profile("Joanne", "Rowling", DateController.seederDate("31-07-1965"));
        ArrayList<Book> books3 = new ArrayList<>();
        Author author3 = new Author(authorProfile3, books3);
        AuthorRepository.authors.add(author3);

        Book book1 = new Book("123456789", "Harry Potter 1", AuthorRepository.authors.get(2),
                DateController.seederDate("26-06-1997"),true);
        BookRepository.allBooks.add(book1);
        Book book2 = new Book("987654321", "Holly", AuthorRepository.authors.get(0),
                DateController.seederDate("05-09-2023"),true);
        BookRepository.allBooks.add(book2);
        Book book3 = new Book("125693478", "Atomic Habits", AuthorRepository.authors.get(1),
                DateController.seederDate("16-10-2018"),true);
        BookRepository.allBooks.add(book3);
    }
}
