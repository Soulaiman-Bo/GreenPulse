import Config.DbConnection;
import Services.UserService;
import Utils.CarbonView;
import Utils.ConsolePrinter;
import Utils.UserView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        initConnection();
        while (true){
            ConsolePrinter.mainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    UserView.userAccountManagement(scanner, userService);
                    break;
                case 2:
                    CarbonView.carbonFingerPrintManagement(scanner, userService);
                    break;
                case 3:
                    ConsolePrinter.printInfo("Exiting...");
                    System.exit(0);
                default:
                    ConsolePrinter.printError("Invalid choice. Please try again.");
            }
        }

    }

    private static void initConnection (){
        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            ConsolePrinter.printInfo("Connected to the database!");

        } catch (SQLException e) {
            ConsolePrinter.printError("Failed to connect to the database!");
            e.printStackTrace();
        }
    }

}
