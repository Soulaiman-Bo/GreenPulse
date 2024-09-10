package Utils;



import Services.UserService;

import java.util.Scanner;

public class UserView {
     static UserService userService = new UserService();

     public static void userAccountManagement( Scanner scanner){
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
        System.out.print("  ==> Entre FullName: ");
        String name = scanner.nextLine();
        System.out.print("  ==> Entre Email: ");
        String email = scanner.nextLine();
        // scanner.nextLine();
        userService.createAccount(name, email);
    }

    public static void modifyAccount(Scanner scanner, UserService userService){
        System.out.print("  ==> Enter user ID to modify: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("  ==> Entre FullName: ");
        String newName = scanner.nextLine();
        System.out.print("  ==> Entre Email: ");
        String newEmail = scanner.nextLine();

       userService.modifyAccount(newName, newEmail, id);
    }

    public static void deleteAccount(Scanner scanner, UserService userService){
        System.out.print("  ==> Enter user ID to delete: ");
        Integer id = scanner.nextInt();
        userService.deleteAccount(id);
    }

    public static void displayAllAccounts(UserService userService){
        userService.displayAllAccounts();
    }

}
