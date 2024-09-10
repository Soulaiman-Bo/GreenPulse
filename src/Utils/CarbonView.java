package Utils;

import Domain.ConsumptionDAO;
import Domain.FoodDao;
import Entities.CarbonConsumption;
import Entities.Food;
import Entities.Housing;
import Entities.Transport;
import Entities.enums.FoodType;
import Entities.enums.HousingType;
import Entities.enums.TransportType;
import Services.ConsumptionService;
import Services.FoodService;
import Services.UserService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CarbonView {

    public static void carbonFingerPrintManagement(Scanner scanner) throws SQLException {

        carbonLoop:
        while (true){
            ConsolePrinter.carbonMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCarbonConsumption(scanner);
                    break;
                case 2:
                    getTotalCarbonConsumption(scanner);
                    break;
                case 3:
                    getAllCarbonConsumptions(scanner);
                    break;
                case 4:
                    getCarbonConsumptionReport(scanner);
                    break;
                case 5:
                    ConsolePrinter.printInfo("Exiting...");
                    break carbonLoop;
                default:
                    ConsolePrinter.printError("Invalid choice. Please try again.");
            }
        }
    }

    public static void addCarbonConsumption(Scanner scanner) throws SQLException {
        System.out.print("==> Enter user ID: ");
        Integer id = scanner.nextInt();

        scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println(" ==> Enter Start date and time (yyyy-MM-dd): ");
        String startDate = scanner.nextLine();

        System.out.println(" ==> Enter End date and time (yyyy-MM-dd): ");
        String endDate = scanner.nextLine();


        ConsolePrinter.ConsumptionTypeMenu();
        int carbon = scanner.nextInt();


        ConsumptionService consumptionService = null;
        switch (carbon){
            case 1:
                System.out.print(" ==> Enter Amount: ");
                Double amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.print(" ==> Entre Distance Travelled: ");
                Integer distanceTravelled = scanner.nextInt();
                scanner.nextLine();
                System.out.print(" ==> Entre Transport Type: ");
                String transportType = scanner.nextLine();
                CarbonConsumption transportConsumption = new Transport(amount, distanceTravelled, TransportType.valueOf(transportType), LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter), id);

                break;
            case 2:
                System.out.print("==> Enter Amount");
                Double amountt = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("==> Entre Energy Consumption: ");
                Integer energyConsumption = scanner.nextInt();
                scanner.nextLine();
                System.out.print("==> Entre Housing Type: ");
                String housingType = scanner.nextLine();

                CarbonConsumption housingConsumption = new Housing(amountt, energyConsumption, HousingType.valueOf(housingType), LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter), id);

                break;
            case  3:
                System.out.print("==> Enter Amount: ");
                Double volume = scanner.nextDouble();
                System.out.print("==> Entre Weight Consumption: ");
                Double weightConsumption = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("==> Entre Food Type: ");
                String foodType = scanner.nextLine();

                CarbonConsumption foodConsumption = new Food(volume, FoodType.valueOf(foodType), weightConsumption , LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter), id);
                FoodService foodService = new FoodService();
                foodService.save(foodConsumption);

                break;
                case  4:

                    break;
        }


    }

    public static void getTotalCarbonConsumption(Scanner scanner){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

//        if (!userService.userExists(id)) {
//            System.out.println("Entities.User not found.");
//            return;
//        }
//        userService.getTotalConsumptionVolume(id);
    }

    public static void getAllCarbonConsumptions(Scanner scanner){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

//        if (!userService.userExists(id)) {
//            System.out.println("Entities.User not found.");
//            return;
//        }
//
//        List<CarbonConsumption> consumptions = userService.getConsumptions(id);
//
//
//        for (CarbonConsumption c : consumptions) {
//            System.out.println(
//                    "Volume: " + c.getVolume() + "\n" +
//                            "Start Date: " + c.getStartDate()+ "\n" +
//                            "End Date: " + c.getEndDate()+ "\n"
//            );
//        }

    }

    public static void getCarbonConsumptionReport(Scanner scanner){
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();

//        if (!userService.userExists(id)) {
//            System.out.println("Entities.User not found.");
//            return;
//        }

        carbonReport:
        while (true){
            ConsolePrinter.reportMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
//                    userService.getConsumptionReportDaily(id);
                    break;
                case 2:
//                    userService.getConsumptionReportWeekly(id);
                    break;
                case 3:
//                    userService.getConsumptionReportMonthly(id);
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
