
import Utils.CarbonView;
import Utils.ConsolePrinter;
import Utils.UserView;

import java.sql.SQLException;
import java.util.*;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        while (true) {
            ConsolePrinter.mainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    UserView.userAccountManagement(scanner);
                    break;
                case 2:
                    CarbonView.carbonFingerPrintManagement(scanner);
                    break;
                case 3:
                    ConsolePrinter.printInfo("Exiting...");
                    System.exit(0);
                    break;
                case 4:
                        //
                    break;
                    
                default:
                    ConsolePrinter.printError("Invalid choice. Please try again.");
            }
        }

    }

}
