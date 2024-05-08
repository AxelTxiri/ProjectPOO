import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {
    public static Scanner scanner = new Scanner(System.in);
    public static UserRepository userRepository = new UserRepository();
    public static AuthorRepository authorRepository = new AuthorRepository();
    public void mainMenu(){
        Menu mainMenuAdmin = new Menu();
        Controller menuBook = new MenuBook();
        Controller menuClient = new MenuClient();
        Controller menuAuthor = new MenuAuthor();
        Controller menuTransaction = new MenuTransaction();
        Controller menuAdmin = new MenuAdmin();
        Controller exit = new exit();

        mainMenuAdmin.addMenuItem(1, new MenuItem("BOOK MENU", menuBook));
        mainMenuAdmin.addMenuItem(2, new MenuItem("CLIENT MENU", menuClient));
        mainMenuAdmin.addMenuItem(3, new MenuItem("AUTHOR MENU", menuAuthor));
        mainMenuAdmin.addMenuItem(4, new MenuItem("TRANSACTION MENU", menuTransaction));
        mainMenuAdmin.addMenuItem(5, new MenuItem("ADMIN MENU", menuAdmin));
        mainMenuAdmin.addMenuItem(6, new MenuItem("LOG OUT", exit));

        mainMenuAdmin.display();
    }

    static class MenuBook implements Controller {
        @Override
        public void execute(){
            /*-Menu menuBook = new Menu();
            Controller controller1 = () -> firstOption(); // Lambda expression
            Controller controller2 = this::secondOption; // Method reference
            controller2.execute();
            subMenu.addMenuItem(1, new MenuItem("FIRST SUB OPTION", controller1));
            subMenu.addMenuItem(2, new MenuItem("SECOND SUB OPTION", controller2));

            subMenu.display();*/
        }
    }
    static class MenuClient implements Controller {
        @Override
        public void execute(){
            Menu menuClient = new Menu();
            Controller createClient = this::createClient;
            Controller readClient = this::readClient;
            Controller updateClient = this::updateClient;
            Controller deleteClient = this::deleteClient;
            Controller mainMenu = this::mainMenu;
            menuClient.addMenuItem(1, new MenuItem("CREATE CLIENT", createClient));
            menuClient.addMenuItem(2, new MenuItem("READ CLIENT", readClient));
            menuClient.addMenuItem(3, new MenuItem("UPDATE CLIENT", updateClient));
            menuClient.addMenuItem(4, new MenuItem("DELETE CLIENT", deleteClient));
            menuClient.addMenuItem(5, new MenuItem("MAIN MENU", mainMenu));

            menuClient.display();
        }
        public void createClient (){
            Profile profile = ProfileController.profileBuilder();
            ArrayList<Book> borrowedBooks = new ArrayList<>();
            System.out.println("Enter the username:");
            String username = scanner.nextLine();
            System.out.println("Enter the password:");
            String password = scanner.nextLine();

            userRepository.createClient(profile,username,password,borrowedBooks);
        }
        public void readClient(){
            if(containsClients()){
                System.out.println("There's no clients in the database.");
                System.out.println();
            }
            else {
                System.out.println("CLIENT LIST:");
                userRepository.readClient();
                System.out.println();
            }
        }
        public void updateClient() {
            if (containsClients()) {
                System.out.println("There's no clients in the database.");
                System.out.println();
            } else {
                System.out.println("CLIENT LIST:");
                userRepository.readClient();
                System.out.println();

                System.out.println("Enter the ID of the client you want to update:");
                int ID = scanner.nextInt();
                scanner.nextLine();

                Profile updatedProfile = ProfileController.profileBuilder();
                System.out.println("Enter the new username:");
                String username = scanner.nextLine();
                System.out.println("Enter the new password:");
                String password = scanner.nextLine();
                userRepository.updateClient(ID, updatedProfile, username, password);
            }
        }
        public void deleteClient(){
            if(containsClients()) {
                System.out.println("There's no clients in the database.");
                System.out.println();
            }
            else{
                System.out.println("CLIENT LIST:");
                userRepository.readClient();
                System.out.println();

                System.out.println("Enter the ID of the client you want to delete:");
                int ID = scanner.nextInt();
                scanner.nextLine();

                userRepository.deleteClient(ID);
            }
        }
        public void mainMenu(){
            System.out.println("Going to main menu");
            System.out.println();
        }
    }
    static class MenuAuthor implements Controller {
        @Override
        public void execute(){
            Menu menuAuthor = new Menu();
            Controller createAuthor = this::createAuthor;
            Controller readAuthor = this::readAuthor;
            Controller updateAuthor = this::updateAuthor;
            Controller deleteAuthor = this::deleteAuthor;
            Controller mainMenu = this::mainMenu;
            menuAuthor.addMenuItem(1, new MenuItem("CREATE AUTHOR", createAuthor));
            menuAuthor.addMenuItem(2, new MenuItem("READ AUTHOR", readAuthor));
            menuAuthor.addMenuItem(3, new MenuItem("UPDATE AUTHOR", updateAuthor));
            menuAuthor.addMenuItem(4, new MenuItem("DELETE AUTHOR", deleteAuthor));
            menuAuthor.addMenuItem(5, new MenuItem("MAIN MENU", mainMenu));

            menuAuthor.display();
        }
        public void createAuthor (){
            Profile profile = ProfileController.profileBuilder();
            ArrayList<Book> books = new ArrayList<>();

            authorRepository.createAuthor(profile, books);
        }
        public void readAuthor(){
            if(AuthorRepository.authors.isEmpty()){
                System.out.println("There's no authors to show");
                System.out.println();
            }else{
                System.out.println("AUTHOR LIST:");
                authorRepository.readAuthor();
            }
        }
        public void updateAuthor() {
            if(AuthorRepository.authors.isEmpty()){
                System.out.println("There's no authors to update.");
                System.out.println();
            }else{
                authorRepository.readAuthor();
                System.out.println();
                System.out.println("Enter the ID of the author you want to update:");
                int ID = scanner.nextInt();
                scanner.nextLine();

                Profile updatedProfile = ProfileController.profileBuilder();
                authorRepository.updateAuthor(ID, updatedProfile);
            }
        }
        public void deleteAuthor(){
            if(AuthorRepository.authors.isEmpty()){
                System.out.println("There's no authors to delete.");
                System.out.println();
            }
            else{
                authorRepository.readAuthor();
                System.out.println();
                System.out.println("Enter the # of the Model.Author to delete:");
                int ID = scanner.nextInt();
                scanner.nextLine();
                authorRepository.deleteAuthor(ID);
            }
        }
        public void mainMenu(){
            System.out.println("Going to main menu");
            System.out.println();
        }
    }
    static class MenuTransaction implements Controller {
        @Override
        public void execute(){}
    }
    static class MenuAdmin implements Controller {
        @Override
        public void execute(){}
    }
    static class exit implements Controller {
        @Override
        public void execute(){
            System.out.println("You are exiting.");
        }
    }

    public static boolean containsClients(){
        for (User user : UserRepository.users) {
            if (user instanceof Client) {
                return false;
            }
        }
        return true;
    }
}