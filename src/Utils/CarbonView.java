package Utils;

import Entities.CarbonConsumption;
import Services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CarbonView {

    public static void carbonFingerPrintManagement(Scanner scanner, UserService userService){
        carbonLoop:
        while (true){
            ConsolePrinter.carbonMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCarbonConsumption(scanner, userService);
                    break;
                case 2:
                    getTotalCarbonConsumption(scanner, userService);
                    break;
                case 3:
                    getAllCarbonConsumptions(scanner, userService);
                    break;
                case 4:
                    getCarbonConsumptionReport(scanner, userService);
                    break;
                case 5:
                    ConsolePrinter.printInfo("Exiting...");
                    break carbonLoop;
                default:
                    ConsolePrinter.printError("Invalid choice. Please try again.");
            }
        }
    }

    public static void addCarbonConsumption(Scanner scanner, UserService userService){
        System.out.print("Enter user ID to add: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            System.out.println("Entities.User not found.");
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

    public static void getTotalCarbonConsumption(Scanner scanner, UserService userService){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            System.out.println("Entities.User not found.");
            return;
        }
        userService.getTotalConsumptionVolume(id);
    }

    public static void getAllCarbonConsumptions(Scanner scanner, UserService userService){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            System.out.println("Entities.User not found.");
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

    public static void getCarbonConsumptionReport(Scanner scanner, UserService userService){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

        if (!userService.userExists(id)) {
            System.out.println("Entities.User not found.");
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
