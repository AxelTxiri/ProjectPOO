import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MenuController {
    public static Scanner scanner = new Scanner(System.in);
    public static UserRepository userRepository = new UserRepository();
    public static AuthorRepository authorRepository = new AuthorRepository();
    public static BookRepository bookRepository = new BookRepository();
    public void mainMenuAdmin(){
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
            Menu menuBook = new Menu();
            Controller createBook = this::createBook;
            Controller readBook = this::readBookMenu;
            Controller updateBook = this::updateBook;
            Controller deleteBook = this::deleteBook;
            Controller mainMenu = this::menuBook;
            menuBook.addMenuItem(1,new MenuItem("CREATE BOOK", createBook));
            menuBook.addMenuItem(2,new MenuItem("READ BOOK", readBook));
            menuBook.addMenuItem(3,new MenuItem("UPDATE BOOK", updateBook));
            menuBook.addMenuItem(4,new MenuItem("DELETE BOOK", deleteBook));
            menuBook.addMenuItem(5,new MenuItem("MAIN MENU", mainMenu));

            menuBook.display();
        }
        public void createBook(){
            System.out.println("Enter isbn:");
            String isbn = scanner.nextLine();
            System.out.println("Enter title:");
            String title = scanner.nextLine();
            Author author = authorSelection();
            System.out.println("Publish date:");
            Date publishDate = DateController.dateInput();
            boolean isAvailable = true;

            bookRepository.createBook(isbn,title,author,publishDate,isAvailable);
        }
        public void readBookMenu(){
            if(BookRepository.allBooks.isEmpty()){
                System.out.println("There's no books in the database");
                System.out.println();
            } else {
                Menu readBookMenu = new Menu();
                Controller allBooks = this::allBooks;
                Controller availableBooks = this::availableBooks;
                Controller borrowedBooks = this::borrowedBooks;
                Controller menuBook = this::menuBook;

                readBookMenu.addMenuItem(1, new MenuItem("ALL BOOKS", allBooks));
                readBookMenu.addMenuItem(2, new MenuItem("AVAILABLE BOOKS", availableBooks));
                readBookMenu.addMenuItem(3, new MenuItem("BORROWED BOOKS", borrowedBooks));
                readBookMenu.addMenuItem(4, new MenuItem("BOOK MENU", menuBook));

                readBookMenu.display();
            }
        }
        public void allBooks(){
            System.out.println("BOOK LIST");
            System.out.printf("%-5s %-15s %-15s %-15s %-8s\n","ID","Title","Author","Isbn","Availability");
            for (Book book : BookRepository.allBooks) {
                System.out.printf("%-5s %-15s %-15s %-15s %-8s\n"
                        ,BookRepository.allBooks.indexOf(book),book.getTitle(),book.getAuthor().getProfile().getLastName(),book.getIsbn()
                        ,book.isAvailable());
            }
            System.out.println();
        }
        public void availableBooks(){
            System.out.println("AVAILABLE BOOKS");
            for (Book book : BookRepository.allBooks) {
                if(book.isAvailable()){
                    System.out.printf("%-5s %-15s %-15s %-15s %-8s\n","ID","Title","Author","Isbn","Availability");
                    break;
                }
            }
            for (Book book : BookRepository.allBooks) {
                if(book.isAvailable()){
                    System.out.printf("%-5s %-15s %-15s %-15s %-8s\n"
                            ,BookRepository.allBooks.indexOf(book),book.getTitle(),book.getAuthor().getProfile().getLastName(),book.getIsbn()
                            ,book.isAvailable());
                }
            }
            System.out.println();
        }
        public void borrowedBooks(){
            System.out.println("BORROWED BOOKS");
            for (Book book : BookRepository.allBooks) {
                if(!book.isAvailable()){
                    System.out.printf("%-5s %-15s %-15s %-15s %-8s\n","ID","Title","Author","Isbn","Availability");
                    break;
                }
            }
            for (Book book : BookRepository.allBooks) {
                if(!book.isAvailable()){
                    System.out.printf("%-5s %-15s %-15s %-15s %-8s\n"
                            ,BookRepository.allBooks.indexOf(book),book.getTitle(),book.getAuthor().getProfile().getLastName(),book.getIsbn()
                            ,book.isAvailable());
                }
            }
            System.out.println();
        }
        public void updateBook() {
            if (BookRepository.allBooks.isEmpty()) {
                System.out.println("There's no books in the database");
                System.out.println();
            } else {
                allBooks();
                System.out.println();
                System.out.println("Enter the ID of the book to update");
                int ID = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Enter isbn:");
                String isbnUpdate = scanner.nextLine();
                System.out.println("Enter title:");
                String titleUpdate = scanner.nextLine();
                Author authorUpdate = authorSelection();
                System.out.println("Publish date:");
                Date publishDateUpdate = DateController.dateInput();

                bookRepository.updateBook(ID, isbnUpdate, titleUpdate, authorUpdate
                        , publishDateUpdate);
            }
        }
        public void deleteBook(){
            if(BookRepository.allBooks.isEmpty()){
                System.out.println("There's no books in the database");
                System.out.println();
            } else {
                allBooks();
                System.out.println("Enter the ID of the book to delete");
                int ID = scanner.nextInt();
                scanner.nextLine();
                bookRepository.deleteBook(ID);
            }
        }
        public void menuBook(){
            System.out.println("Going to menu book");
            System.out.println();
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
        public void execute(){
            Menu menuTransaction = new Menu();
            Controller borrowBook = this::borrowBook;
            Controller returnBook = this::returnBook;
            Controller transactionsReportMenu = new TransactionsReportMenu();
            Controller mainMenu = this::mainMenu;
            menuTransaction.addMenuItem(1, new MenuItem("BORROW BOOKS", borrowBook));
            menuTransaction.addMenuItem(2, new MenuItem("RETURN BOOKS", returnBook));
            menuTransaction.addMenuItem(3, new MenuItem("TRANSACTIONS REPORT", transactionsReportMenu));
            menuTransaction.addMenuItem(4, new MenuItem("MAIN MENU", mainMenu));

            menuTransaction.display();
        }
        public void borrowBook (){
            TransactionController.borrowBooks();
        }
        public void returnBook (){
            TransactionController.returnBooks();
        }
        static class TransactionsReportMenu implements Controller {
            @Override
            public void execute(){
                Menu transactionsReportMenu = new Menu();
                Controller reportByDate = this::reportByDate;
                Controller reportByClient = this::reportByClient;
                Controller reportByBook = this::reportByBook;
                Controller transactionMenu = this::transactionMenu;
                transactionsReportMenu.addMenuItem(1, new MenuItem("TRANSACTIONS IN A PERIOD OF TIME", reportByDate));
                transactionsReportMenu.addMenuItem(2, new MenuItem("TRANSACTIONS OF A CLIENT", reportByClient));
                transactionsReportMenu.addMenuItem(3, new MenuItem("TRANSACTIONS OF A BOOK", reportByBook));
                transactionsReportMenu.addMenuItem(4, new MenuItem("TRANSACTION MENU", transactionMenu));

                transactionsReportMenu.display();
            }
            public void reportByDate (){
                if(TransactionRepository.transactions.isEmpty()){
                    System.out.println("There's no transaction in the database");
                    System.out.println();
                } else {
                    Date initialDate = DateController.dateInput();
                    Date finalDate = DateController.dateInput();

                    if(initialDate.after(finalDate)){
                        System.out.println("Initial date is after final date");
                        System.out.println();
                    }else{
                        boolean transactionFound = false;

                        System.out.printf("%-38s %-20s %-20s %-30s %-15s\n", "Transaction ID", "Type", "Client", "Book", "Date");
                        for(Transaction transaction : TransactionRepository.transactions){
                            if(!transaction.getDate().before(initialDate) && !transaction.getDate().after(finalDate)){
                                System.out.printf("%-38s %-20s %-20s %-30s %-15s\n", transaction.getId(),
                                        transaction.getType(), transaction.getClient().getProfile().getName(),
                                        transaction.getBook().getTitle(), transaction.getDate());
                                transactionFound = true;
                            }
                        }

                        if(!transactionFound){
                            System.out.println("There is no transaction in the database between the dates you entered.");
                        }
                    }
                }
            }
            public void reportByClient (){
                if(TransactionRepository.transactions.isEmpty()){
                    System.out.println("There's no transaction in the database");
                    System.out.println();
                } else {
                    userRepository.readClient();
                    System.out.println("Enter the NAME of the client:");
                    String clientName = scanner.nextLine();

                    System.out.printf("%-38s %-20s %-20s %-30s %-15s\n", "Transaction ID", "Type", "Client", "Book", "Date");
                    for(Transaction transaction : TransactionRepository.transactions){
                        if(transaction.getClient().getProfile().getName().equals(clientName)){
                            System.out.printf("%-38s %-20s %-20s %-30s %-15s\n", transaction.getId(),
                                    transaction.getType(), transaction.getClient().getProfile().getName(),
                                    transaction.getBook().getTitle(), transaction.getDate());
                        }
                    }
                }
            }
            public void reportByBook (){
                if(TransactionRepository.transactions.isEmpty()){
                    System.out.println("There's no transaction in the database");
                    System.out.println();
                } else {
                    MenuBook menuBook = new MenuBook();
                    menuBook.allBooks();
                    System.out.println("Enter the TITLE of the book:");
                    String bookTitle = scanner.nextLine();

                    System.out.printf("%-38s %-20s %-20s %-30s %-15s\n", "Transaction ID", "Type", "Client", "Book", "Date");
                    for(Transaction transaction : TransactionRepository.transactions){
                        if(transaction.getBook().getTitle().equals(bookTitle)){
                            System.out.printf("%-38s %-20s %-20s %-30s %-15s\n", transaction.getId(),
                                    transaction.getType(), transaction.getClient().getProfile().getName(),
                                    transaction.getBook().getTitle(), transaction.getDate());
                        }
                    }
                }
            }
            public void transactionMenu(){
                System.out.println("Transactions Report Menu");
                System.out.println();
            }
        }
        public void mainMenu(){
            System.out.println("Going to main menu");
            System.out.println();
        }
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
    public static Author authorSelection(){
        AuthorRepository authorRepository = new AuthorRepository();
        authorRepository.readAuthor();
        System.out.println("Enter the ID of the author of the book: ");
        int ID;
        do{
            ID = scanner.nextInt();
            scanner.nextLine();
            if(ID<0 || ID>AuthorRepository.authors.size()){
                System.out.println("Invalid ID.");
                System.out.println();
                System.out.println("Enter a valid Author ID:");
            }
        }while(ID<0 || ID>AuthorRepository.authors.size());
        return AuthorRepository.authors.get(ID);
    }
}