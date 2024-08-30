import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static UserManager userManager = new UserManager();

    public static void main(String[] args) {

        while (true){
            System.out.println("1. User Account Management");
            System.out.println("2. Carbon Fingerprint");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userAccountManagement();
                    break;
                case 2:
                    carbonFingerPrintManagement();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }

    private static void userAccountManagement(){
        loop:
        while (true){
            System.out.println("\nUser Account Management");
            System.out.println("1. Create Account");
            System.out.println("2. Modify Account");
            System.out.println("3. Delete Account");
            System.out.println("4. Display All Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

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
                    System.out.println("Exiting...");
                    break loop;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void carbonFingerPrintManagement(){
        carbonLoop:
        while (true){
            System.out.println("\ncarbon FingerPrint Management");
            System.out.println("1. Add Carbon Consumption");
            System.out.println("2. Get Total Carbon Consumption");
            System.out.println("3. Get All Carbon Consumption");
            System.out.println("4. Get Carbon Consumption Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

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
                    System.out.println("Exiting...");
                    break carbonLoop;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(){
        System.out.println("Entre name: ");
        String name = scanner.nextLine();
        System.out.println("Entre age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        String id = userManager.createAccount(name, age);
        System.out.println("Your account ID is: " + id);
    }

    private static void modifyAccount(){
        System.out.print("Enter user ID to modify: ");
        String id = scanner.nextLine();

        if (!userManager.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();
        scanner.nextLine();

        userManager.modifyAccount(id, newName, newAge);
    }

    private static void deleteAccount(){
        System.out.print("Enter user ID to delete: ");
        String id = scanner.nextLine();

        if (!userManager.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        userManager.deleteAccount(id);
    }

    private static void displayAllAccounts(){
        System.out.println("\nusers list:  ");
        userManager.displayAllAccounts();
    }

    private static void addCarbonConsumption(){
        System.out.print("Enter user ID to add: ");
        String id = scanner.nextLine();

        if (!userManager.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter Carbon consumption: ");
        String carbon = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("Enter Start date and time (yyyy-MM-dd HH:mm):");
        String startDate = scanner.nextLine();

        System.out.println("Enter End date and time (yyyy-MM-dd HH:mm):");
        String endDate = scanner.nextLine();

        CarbonConsumption consumption1 = new CarbonConsumption(Integer.parseInt(carbon), LocalDateTime.parse(startDate, formatter), LocalDateTime.parse(endDate, formatter));
        userManager.addCarbonConsumption(id, consumption1);

    }

    private static void getTotalCarbonConsumption(){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

        if (!userManager.userExists(id)) {
            System.out.println("User not found.");
            return;
        }
        userManager.getTotalConsumptionVolume(id);
    }

    private static void getAllCarbonConsumptions(){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

        if (!userManager.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        List<CarbonConsumption> consumptions = userManager.getConsumptions(id);


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

        if (!userManager.userExists(id)) {
            System.out.println("User not found.");
            return;
        }

        carbonReport:
        while (true){
            System.out.println("\ncarbon FingerPrint Management");
            System.out.println("1. Daily");
            System.out.println("2. Weekly");
            System.out.println("3. Monthly");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userManager.getConsumptionReportDaily(id);
                    break;
                case 2:
                    userManager.getConsumptionReportWeekly(id);
                    break;
                case 3:
                    userManager.getConsumptionReportMonthly(id);
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
