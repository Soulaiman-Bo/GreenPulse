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
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }

    private static void userAccountManagement(){
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
                    System.exit(0);
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

        int id = userManager.createAccount(name, age);
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

        userManager.deleteAccount(Integer.parseInt(id));
    }

    private static void displayAllAccounts(){
        System.out.println("\nusers list:  ");
        userManager.displayAllAccounts();
    }


}
