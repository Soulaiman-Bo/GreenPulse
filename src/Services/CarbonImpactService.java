package Services;

import Domain.FoodDao;
import Domain.HousingDao;
import Domain.TransportDao;
import Domain.UserDAO;
import Entities.CarbonConsumption;
import Entities.User;
import Utils.UserWithImpact;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarbonImpactService {
    private FoodService foodDao;
    private TransportService transportDao;
    private HousingService housingDao;
    private UserService userDao;

    public CarbonImpactService(FoodService foodDao, TransportService transportDao, HousingService housingDao, UserService userDao) {
        this.foodDao = foodDao;
        this.transportDao = transportDao;
        this.housingDao = housingDao;
        this.userDao = userDao;
    }

    public List<CarbonConsumption> getAllConsumptions() throws SQLException {
        return Stream.of(
                        foodDao.findAll().orElse(List.of()),
                        transportDao.findAll().orElse(List.of()),
                        housingDao.findAll().orElse(List.of())
                ).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<User> getUsersWithHighImpactTwo() throws SQLException {
        List<User> allUsers = userDao.findAll().orElseThrow(() -> new SQLException("Failed to fetch users"));

        return allUsers.stream()
                .filter(user -> {
                    try {
                        double totalImpact = calculateTotalImpact(user.getId());
                        return totalImpact > 3000;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<User> getUsersWithHighImpact() throws SQLException {
        List<User> allUsers = userDao.findAll().orElseThrow(() -> new SQLException("Failed to fetch users"));
        Map<Integer, Double> userImpacts = calculateTotalImpacts();

        return allUsers.stream()
                .filter(user -> userImpacts.getOrDefault(user.getId(), 0.0) > 3000)
                .collect(Collectors.toList());
    }

    private Map<Integer, Double> calculateTotalImpacts() throws SQLException {
        List<CarbonConsumption> allConsumptions = getAllConsumptions();

        DecimalFormat formatter = new DecimalFormat("#,###.##");

        return allConsumptions.stream()
                .collect(Collectors.groupingBy(
                        CarbonConsumption::getUserId,
                        Collectors.summingDouble(CarbonConsumption::calculateImpact)
                ));
    }

    private double calculateTotalImpact(int userId) throws SQLException {
        List<CarbonConsumption> foodConsumptions = foodDao.findAllById(userId).orElse(List.of());
        List<CarbonConsumption> housingConsumptions = housingDao.findAllById(userId).orElse(List.of());
        List<CarbonConsumption> transportConsumptions = transportDao.findAllById(userId).orElse(List.of());

        return Stream.of(foodConsumptions, housingConsumptions, transportConsumptions)
                .flatMap(List::stream)
                .mapToDouble(CarbonConsumption::calculateImpact)
                .sum();
    }

    public List<UserWithImpact> getUsersOrderedByImpact() throws SQLException {
        List<User> allUsers = userDao.findAll().orElseThrow(() -> new SQLException("Failed to fetch users"));
        Map<Integer, Double> userImpacts = calculateTotalImpacts();

        return allUsers.stream()
                .map(user -> new UserWithImpact(user, userImpacts.getOrDefault(user.getId(), 0.0)))
                .sorted((ui1, ui2) -> Double.compare(ui2.getImpact(), ui1.getImpact()))
                .collect(Collectors.toList());
    }




}
