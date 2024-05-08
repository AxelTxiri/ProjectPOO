import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class DateController {
    public static Date dateInput(){
        Scanner scanner = new Scanner(System.in);

        Date date = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        boolean isValidDate = false;
        do{
            System.out.println("Enter date (format: DD-MM-YYYY):");
            String auxDate = scanner.nextLine();
            try{
                date = dateFormat.parse(auxDate);
                isValidDate = true;
            } catch (ParseException e){
                System.out.println("Invalid date.");
                System.out.println("Enter date in DD-MM-YYYY format:");
            }
        }while(!isValidDate);
        return date;
    }
    public static Date seederDate(String seederDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(seederDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
