import Utils.ConsolePrinter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();

    public static void main(String[] args) {

        while (true){
            ConsolePrinter.mainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userAccountManagement();
                    break;
                case 2:
                    carbonFingerPrintManagement();
                    break;
                case 3:
                    ConsolePrinter.printInfo("Exiting...");
                    System.exit(0);
                default:
                    ConsolePrinter.printError("Invalid choice. Please try again.");
            }
        }

    }

    private static void userAccountManagement(){
        loop:
        while (true){
            ConsolePrinter.userManagementMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    modifyAccount();
                    break;
                case 3:
                    deleteAccount();
                    break;
                case 4:
                    displayAllAccounts();
                    break;
                case 5:
                    ConsolePrinter.printInfo("Exiting...");
                    break loop;
                default:
                    ConsolePrinter.printError("Invalid choice. Please try again.");
            }
        }
    }

    private static void carbonFingerPrintManagement(){
        carbonLoop:
        while (true){
            ConsolePrinter.carbonMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCarbonConsumption();
                    break;
                case 2:
                    getTotalCarbonConsumption();
                    break;
                case 3:
                    getAllCarbonConsumptions();
                    break;
                case 4:
                    getCarbonConsumptionReport();
                    break;
                case 5:
                    ConsolePrinter.printInfo("Exiting...");
                    break carbonLoop;
                default:
                    ConsolePrinter.printError("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(){
        System.out.print("  ==> Entre name: ");
        String name = scanner.nextLine();
        System.out.print("  ==> Entre age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        String id = userService.createAccount(name, age);
        ConsolePrinter.printInfo("Your account ID is: " + id);

    }

    private static void modifyAccount(){
        System.out.print("  ==> Enter user ID to modify: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            ConsolePrinter.printError("User not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();
        scanner.nextLine();

        userService.modifyAccount(id, newName, newAge);
    }

    private static void deleteAccount(){
        System.out.print("Enter user ID to delete: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        userService.deleteAccount(id);
    }

    private static void displayAllAccounts(){
        System.out.println("\nusers list:  ");
        userService.displayAllAccounts();
    }

    private static void addCarbonConsumption(){
        System.out.print("Enter user ID to add: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter Carbon consumption: ");
        String carbon = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("Enter Start date and time (yyyy-MM-dd):");
        String startDate = scanner.nextLine();

        System.out.println("Enter End date and time (yyyy-MM-dd):");
        String endDate = scanner.nextLine();

        CarbonConsumption consumption1 = new CarbonConsumption(
                Integer.parseInt(carbon),
                LocalDate.parse(startDate, formatter),
                LocalDate.parse(endDate, formatter),
                id);

        userService.addCarbonConsumption(id, consumption1);

    }

    private static void getTotalCarbonConsumption(){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            System.out.println("User not found.");
            return;
        }
        userService.getTotalConsumptionVolume(id);
    }

    private static void getAllCarbonConsumptions(){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        List<CarbonConsumption> consumptions = userService.getConsumptions(id);


        for (CarbonConsumption c : consumptions) {
            System.out.println(
                    "Volume: " + c.getVolume() + "\n" +
                    "Start Date: " + c.getStartDate()+ "\n" +
                    "End Date: " + c.getEndDate()+ "\n"
            );
        }

    }

    private static void getCarbonConsumptionReport(){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        carbonReport:
        while (true){
            ConsolePrinter.reportMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userService.getConsumptionReportDaily(id);
                    break;
                case 2:
                    userService.getConsumptionReportWeekly(id);
                    break;
                case 3:
                    userService.getConsumptionReportMonthly(id);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break carbonReport;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }


}
