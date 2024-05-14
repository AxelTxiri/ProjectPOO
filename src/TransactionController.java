import java.util.ArrayList;
import java.util.Scanner;

public class TransactionController {
    public static Scanner scanner = new Scanner(System.in);

    public static void borrowBooks(){
        MenuController.MenuBook menuBook = new MenuController.MenuBook();
        menuBook.availableBooks();
        System.out.println("Enter the ID of the book you want to borrow:");
        int bookID = scanner.nextInt();
        scanner.nextLine();

        UserRepository userRepository = new UserRepository();
        userRepository.readClient();
        System.out.println("Enter the ID of the user you want to borrow them the book:");
        int userID = scanner.nextInt();
        scanner.nextLine();

        if(UserRepository.users.get(userID) instanceof Client client){
            if(client.getBorrowedBooks().size() >= 3){
                System.out.println("Each client can only have 3 books.");
            } else {
                ArrayList<Book> borrowedBooks = new ArrayList<>();
                borrowedBooks.add(BookRepository.allBooks.get(bookID));
                BookRepository.allBooks.get(bookID).setAvailable(false);
                client.setBorrowedBooks(borrowedBooks);
                System.out.println("Book successfully borrowed to "+client.getProfile().getName()+" "+client.getProfile().getLastName());
                System.out.println();
            }
        }
    }
    public static void returnBooks(){
        for(User user : UserRepository.users){
            if(user instanceof Client client){
                if(!client.getBorrowedBooks().isEmpty()){
                    System.out.printf("%-5s %-10s %-15s %-25s\n", "ID", "Name", "Last Name", "Borrowed Books");
                    break;
                }
            }
        }
        for(User user : UserRepository.users){
            if(user instanceof Client client){
                if(!client.getBorrowedBooks().isEmpty()){
                    System.out.printf("%-5s %-10s %-15s %-25s"
                            , UserRepository.users.indexOf(client), client.getProfile().getName(), client.getProfile().getLastName(), "Borrowed Books:");
                    for (Book book : client.getBorrowedBooks()) {
                        System.out.print(book.getTitle() + " ");
                    }
                    System.out.println();
                }
            }
        }
        System.out.println();
        System.out.println("Enter the ID of the client who is returning a book.");
        int clientID = scanner.nextInt();
        scanner.nextLine();

        for(User user : UserRepository.users){
            if(user instanceof Client client){
                if(!client.getBorrowedBooks().isEmpty()){
                    System.out.printf("%-5s %-15s %-15s\n", "ID", "Title", "Author");
                    break;
                }
            }
        }
        for(User user : UserRepository.users){
            if(user instanceof Client client){
                for(Book book : client.getBorrowedBooks()){
                    System.out.printf("%-5s %-15s %-15s\n"
                            , BookRepository.allBooks.indexOf(book), book.getTitle(), book.getAuthor().getProfile().getLastName());
                }
            }
        }
        System.out.println();
        System.out.println("Enter the ID of the book you want to return:");
        int bookID = scanner.nextInt();
        scanner.nextLine();

        if(UserRepository.users.get(clientID) instanceof Client client){
            client.getBorrowedBooks().get(bookID).setAvailable(true);
            client.getBorrowedBooks().remove(bookID);
            System.out.println("Book returned successfully.");
            System.out.println();
        }
    }
}
