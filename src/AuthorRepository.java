import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AuthorRepository {
    public static ArrayList<Author> authors = new ArrayList<>();

    public void createAuthor(Profile profile, ArrayList<Book> books){
        Author author = new Author(profile, books);
        authors.add(author);
        System.out.println("Author successfully created.");
    }
    public void readAuthor(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.printf("%-5s %-10s %-15s %-15s %-10s\n","ID","Name","Last Name","Birthdate","Books Title");
        for (Author author : authors) {
            System.out.printf("%-5s %-10s %-15s %-15s %-10s"
                    , authors.indexOf(author), author.getProfile().getName(), author.getProfile().getLastName()
                    , dateFormat.format(author.getProfile().getBirthdate()), "Books:");
            for (Book book : author.getBooks()) {
                System.out.print(book.getTitle() + ", ");
            }
            System.out.println();
        }
    }
    public void updateAuthor(int id, Profile updatedProfile){
        if (updatedProfile.getName() == ""){
            updatedProfile.setName(authors.get(id).getProfile().getName());
        }
        if (updatedProfile.getLastName() == ""){
            updatedProfile.setLastName(authors.get(id).getProfile().getLastName());
        }
        Author author = new Author(updatedProfile,authors.get(id).getBooks());
        authors.set(id, author);
        System.out.println("Author successfully updated.");
    }
    public void deleteAuthor(int id){
        if(authors.get(id).getBooks().isEmpty()){
            System.out.println("Delete author's books to delete the author.");
        }
        authors.remove(id);
        System.out.println("Successfully deleted.");
        System.out.println();
    }
}
