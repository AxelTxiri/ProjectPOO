import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserRepository {
    public static ArrayList<User> users = new ArrayList<User>();

    public void createClient(Profile profile,String username, String password, ArrayList<Book>borrowedBooks){
        User client = new Client(profile,username,password, borrowedBooks);
        users.add(client);
        System.out.println("Client successfully created.");
    }
    public void readClient() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        System.out.printf("%-5s %-10s %-15s %-15s %-10s\n","ID","Name","Last Name","Birthdate","Books Title");
        int clientID;
        for(User user:users){
            if (user instanceof Client client){
                clientID = users.indexOf(client);
                System.out.printf("%-5s %-10s %-15s %-15s %-10s"
                        ,clientID,client.getProfile().getName(),client.getProfile().getLastName()
                        ,dateFormat.format(client.getProfile().getBirthdate()),"Books:");
                for (Book book : client.getBorrowedBooks()) {
                    System.out.print(book.getTitle() + " ");
                }
                System.out.println();
            }
        }
    }
    public void updateClient(int id, Profile updatedProfile, String username, String password) {
        if (users.get(id) instanceof Client client){
            if (updatedProfile.getName() == ""){
                updatedProfile.setName(users.get(id).getProfile().getName());
            }
            if (updatedProfile.getLastName() == ""){
                updatedProfile.setLastName(users.get(id).getProfile().getLastName());
            }
            if (username == ""){
                username = users.get(id).getUsername();
            }
            if (password == ""){
                password = users.get(id).getPassword();
            }
            User updatedClient = new Client(updatedProfile,username,password, client.getBorrowedBooks());
            users.set(id, updatedClient);
            System.out.println("Client successfully updated.");
            System.out.println();
        }
    }
    public void deleteClient(int id) {
        if(users.get(id) instanceof Client client){
            if(!client.getBorrowedBooks().isEmpty()){
                System.out.println("Return client's books to delete the client");
            } else {
                users.remove(id);
                System.out.println("Client successfully deleted.");
                System.out.println();
            }
        }
    }
}

