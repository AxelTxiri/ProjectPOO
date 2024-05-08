import java.util.Date;
import java.util.Scanner;

public class ProfileController {
    public static Scanner scanner = new Scanner(System.in);
    public static Profile profileBuilder(){
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Last name:");
        String lastName = scanner.nextLine();
        Date birthdate = DateController.dateInput();

        return new Profile(name,lastName,birthdate);
    }
}
