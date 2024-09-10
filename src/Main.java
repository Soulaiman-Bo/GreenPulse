
import Domain.ConsumptionDAO;
import Domain.UserDAO;
import Entities.*;
import Entities.enums.ConsumptionType;
import Entities.enums.FoodType;
import Entities.enums.HousingType;
import Entities.enums.TransportType;
import Services.ConsumptionService;
import Services.FoodService;
import Services.UserService;
import Utils.CarbonView;
import Utils.ConsolePrinter;
import Utils.UserView;

import java.sql.SQLException;
import java.time.LocalDate;
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
                case 4:
                    FoodService foodService = new FoodService();
                    List<CarbonConsumption> result =  foodService.findAll(10);
                    System.out.println(result);
                default:
                    ConsolePrinter.printError("Invalid choice. Please try again.");
            }
        }






        //  User user = new User("khalid bouhlal", "soulaimakhalidnbouhlal@gmail.com", 4);
      //  UserDAO userDAO = new UserDAO();
//
     //  userDAO.save(user);
        // userDAO.update(user);

      //  userDAO.delete(4);

//         User usr =  userDAO.findById(8);
//        System.out.println(usr.getEmail());

//         List<User> users = userDAO.findAll();
//         users.stream().forEach(System.out::println);


//        CarbonConsumption consumption = new Transport(
//                22,
//                33,
//                TransportType.TRAIN,
//                LocalDate.of(2024, 12, 3),
//                LocalDate.of(2024, 11, 3),
//                5);
//
//        CarbonConsumption consumptionFood = new Food(
//               55,
//                FoodType.MEAT,
//                33,
//                LocalDate.of(2024, 12, 3),
//                LocalDate.of(2024, 11, 3),
//                6
//                );
//
//        CarbonConsumption consumptionHousing = new Housing(
//                55,
//                44,
//                HousingType.ELECTRICITY,
//                LocalDate.of(2024, 12, 3),
//                LocalDate.of(2024, 11, 3),
//                6
//        );
//
//        // ConsumptionDAO consumptionDAO = new ConsumptionDAO();
//        ConsumptionService consumptionService = new ConsumptionService();
//
//        List<CarbonConsumption> consumptions =  consumptionService.getConsumptions(6);
//
//        consumptions.stream().forEach(System.out::println);


    }

}
