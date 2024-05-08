public class MainController {
    public static void main(String[] args) {
        Seeder seeder = new Seeder();
        seeder.sow();
        LoginController loginController = new LoginController();
        loginController.login();
    }
}
