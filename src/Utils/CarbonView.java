package Utils;

import Entities.*;
import Entities.enums.FoodType;
import Entities.enums.HousingType;
import Entities.enums.TransportType;
import Services.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CarbonView {

    static FoodService foodService = new FoodService();
    static TransportService transportService = new TransportService();
    static HousingService housingService = new HousingService();
    static UserService userService = new UserService();

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
                    getHighImpactUsers();
                    break;
                case 6:
                    getAverageConsumptionByPeriod(scanner);
                    break;
                case 7:
                    getInActiveUsers(scanner);
                    break;
                case 8:
                    getUserImpactOrdered();
                    break;
                case 9:
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

        switch (carbon){
            case 1:
                System.out.print(" ==> Enter Amount: ");
                Double amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.print(" ==> Entre Distance Travelled: ");
                Double distanceTravelled = scanner.nextDouble();
                scanner.nextLine();
                System.out.print(" ==> Entre Transport Type: ");
                String transportType = scanner.nextLine();

                CarbonConsumption transportConsumption = new Transport(amount, distanceTravelled, TransportType.valueOf(transportType), LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter), id);
                transportService.save(transportConsumption);

                break;
            case 2:
                System.out.print("==> Enter Amount");
                Double amountt = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("==> Entre Energy Consumption: ");
                Double energyConsumption = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("==> Entre Housing Type: ");
                String housingType = scanner.nextLine();

                CarbonConsumption housingConsumption = new Housing(amountt, energyConsumption, HousingType.valueOf(housingType), LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter), id);
                housingService.save(housingConsumption);

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
        Integer id = scanner.nextInt();
        scanner.nextLine();

        Optional<User> user = userService.findById(id);
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

    public static void getHighImpactUsers() throws SQLException {
        CarbonImpactService service = new CarbonImpactService(foodService, transportService, housingService, userService);

        List<User> highImpactUsers = service.getUsersWithHighImpact();
        highImpactUsers.forEach(user -> System.out.println(user.getFull_name() + " has a high impact"));

    }

    public static void getUserImpactOrdered() throws SQLException {
        CarbonImpactService service = new CarbonImpactService(foodService, transportService, housingService, userService);
        List<UserWithImpact> orderedUsers = service.getUsersOrderedByImpact();
        orderedUsers.forEach(userImpact -> {
            User user = userImpact.getUser();
            Double impact = userImpact.getImpact();
            String fullName = user.getFull_name();
            int userId = user.getId();
            System.out.println("ID: " + userId + ", Full Name: " + fullName + ", Impact: " + impact);
        });
    }

    public static void getAverageConsumptionByPeriod(Scanner scanner) throws SQLException {
        System.out.print("==> Enter User ID: ");
        Integer user_id = scanner.nextInt();
        scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println(" ==> Enter Start date and time (yyyy-MM-dd): ");
        String startDate = scanner.nextLine();

        System.out.println(" ==> Enter End date and time (yyyy-MM-dd): ");
        String endDate = scanner.nextLine();

        Optional<User> user = userService.findById(user_id);
        CarbonImpactService service = new CarbonImpactService(foodService, transportService, housingService, userService);
        List<CarbonConsumption> consumptions = service.getAllConsumptionsForUser(user_id);
        user.get().setCarbonConsumption(consumptions);

        Double average =  service.getAverageByPeriod(user.get(), LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter));

        System.out.println("User ID: " + user_id + " Average: " + average );

    }

    public static void getInActiveUsers(Scanner scanner) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println(" ==> Enter Start date and time (yyyy-MM-dd): ");
        String startDate = scanner.nextLine();

        System.out.println(" ==> Enter End date and time (yyyy-MM-dd): ");
        String endDate = scanner.nextLine();

        CarbonImpactService service = new CarbonImpactService(foodService, transportService, housingService, userService);
        List<User> users =  service.getInactiveUsers(LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter));

        users.forEach(System.out::println);

    }
}
