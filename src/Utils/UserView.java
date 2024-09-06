package Utils;

import Services.UserService;

import java.util.Scanner;

public class UserView {

    public static void userAccountManagement( Scanner scanner, UserService userService){
        loop:
        while (true){
            ConsolePrinter.userManagementMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner, userService);
                    break;
                case 2:
                    modifyAccount(scanner, userService);
                    break;
                case 3:
                    deleteAccount(scanner, userService);
                    break;
                case 4:
                    displayAllAccounts(userService);
                    break;
                case 5:
                    ConsolePrinter.printInfo("Exiting...");
                    break loop;
                default:
                    ConsolePrinter.printError("Invalid choice. Please try again.");
            }
        }
    }

    public static void createAccount(Scanner scanner, UserService userService){
        System.out.print("  ==> Entre name: ");
        String name = scanner.nextLine();
        System.out.print("  ==> Entre age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        String id = userService.createAccount(name, age);
        ConsolePrinter.printInfo("Your account ID is: " + id);

    }

    public static void modifyAccount(Scanner scanner, UserService userService){
        System.out.print("  ==> Enter user ID to modify: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            ConsolePrinter.printError("Entities.User not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();
        scanner.nextLine();

        userService.modifyAccount(id, newName, newAge);
    }

    public static void deleteAccount(Scanner scanner, UserService userService){
        System.out.print("Enter user ID to delete: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            System.out.println("Entities.User not found.");
            return;
        }

        userService.deleteAccount(id);
    }

    public static void displayAllAccounts(UserService userService){
        System.out.println("\nusers list:  ");
        userService.displayAllAccounts();
    }
}
