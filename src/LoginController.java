import java.util.Scanner;

public class LoginController {

    public static PasswordController passwordController = new PasswordController();
    public static UserRepository userRepository = new UserRepository();

    public void login () {
        int attempts = 0;
        Scanner scanner = new Scanner(System.in);


        System.out.println("******************************");
        System.out.println("*           LOGIN            *");
        System.out.println("******************************");

        do{
            System.out.println("Enter your username:");
            String username = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            password = PasswordController.hashString(password);

            if(validate(username, password)) {
                System.out.println("Login Successful");
                System.out.println();
                MenuController menuController = new MenuController();
                menuController.mainMenuAdmin();
                attempts=0;
            } else {
                System.out.println("Invalid username or password. Try again");
                System.out.println();
            }

            attempts++;
        }while(attempts < 3);
    }
    private static boolean validate(String username, String password) {
        for (User user: UserRepository.users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}

