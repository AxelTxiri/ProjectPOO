import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private HashMap<Integer, MenuItem> menuItems;

    Menu() {
        menuItems = new HashMap<>();
    }

    void display() {
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("MENU: ");
            for (Integer key : menuItems.keySet()) {
                MenuItem menuItem = menuItems.get(key);
                System.out.println(key + " - " + menuItem.getText());
            }

            option = scanner.nextInt();
            scanner.nextLine();

            if (option < 1 || option > menuItems.size()) {
                System.out.println("Invalid option. Please try again.");
                System.out.println();
                continue;
            }

            MenuItem menuItem = menuItems.get(option);
            menuItem.getController().execute();

            if(option != menuItems.size()){
                option = 0;
            }

        }while(option<1 || option>menuItems.size());
    }

    void addMenuItem(int key, MenuItem menuItem) {
        menuItems.put(key, menuItem);
    }
}
