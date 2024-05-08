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

        Profile profile3 = new Profile("Judith", "Martinez", DateController.seederDate("12-03-2001"));
        ArrayList<Book> books = new ArrayList<>();
        Author author = new Author(profile3, books);
        AuthorRepository.authors.add(author);
    }
}
