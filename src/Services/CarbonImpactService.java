package Services;

import Entities.CarbonConsumption;
import Entities.User;
import Utils.DateUtils;
import Utils.UserWithImpact;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarbonImpactService {
    private final FoodService foodService;
    private final TransportService transportService;
    private final HousingService housingService;
    private final UserService userService;

    public CarbonImpactService(FoodService foodService, TransportService transportService, HousingService housingService, UserService userService) {
        this.foodService = foodService;
        this.transportService = transportService;
        this.housingService = housingService;
        this.userService = userService;
    }

    public List<CarbonConsumption> getAllConsumptions() throws SQLException {
        return Stream.of(
                        foodService.findAll().orElse(List.of()),
                        transportService.findAll().orElse(List.of()),
                        housingService.findAll().orElse(List.of())
                ).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<CarbonConsumption> getAllConsumptionsForUser(int userId) throws SQLException {
        return Stream.of(
                        foodService.findAllById(userId).orElse(List.of()),
                        transportService.findAllById(userId).orElse(List.of()),
                        housingService.findAllById(userId).orElse(List.of())
                )
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<User> getUsersWithHighImpact() throws SQLException {
        List<User> allUsers = userService.findAll().orElseThrow(() -> new SQLException("Failed to fetch users"));
        Map<Integer, Double> userImpacts = calculateTotalImpacts();

        return allUsers.stream()
                .filter(user -> userImpacts.getOrDefault(user.getId(), 0.0) > 3000)
                .collect(Collectors.toList());
    }

    private Map<Integer, Double> calculateTotalImpacts() throws SQLException {
        List<CarbonConsumption> allConsumptions = getAllConsumptions();

        return allConsumptions.stream()
                .collect(Collectors.groupingBy(
                        CarbonConsumption::getUserId,
                        Collectors.summingDouble(CarbonConsumption::calculateImpact)
                ));
    }

    public List<UserWithImpact> getUsersOrderedByImpact() throws SQLException {
        List<User> allUsers = userService.findAll().orElseThrow(() -> new SQLException("Failed to fetch users"));
        Map<Integer, Double> userImpacts = calculateTotalImpacts();

        return allUsers.stream()
                .map(user -> new UserWithImpact(user, userImpacts.getOrDefault(user.getId(), 0.0)))
                .sorted((ui1, ui2) -> Double.compare(ui2.getImpact(), ui1.getImpact()))
                .collect(Collectors.toList());
    }

    public Double getAverageByPeriod(User user, LocalDate start , LocalDate endDate) {
        if (!start.isAfter(endDate)) {
            List<CarbonConsumption> consumptions = user.getCarbonConsumption();
            List<LocalDate> dates = DateUtils.dateListRange(start, endDate);

            return (consumptions
                    .stream()
                    .filter(e -> !DateUtils.verifyDateExistence(e.getStartDate(), e.getEndDate(), dates))
                    .mapToDouble(CarbonConsumption::calculateImpact).sum()) / dates.size();
        }
        return 0.0;
    }


    public List<User> getInactiveUsers(LocalDate startDate, LocalDate endDate) throws SQLException {
        List<User> allUsers = userService.findAll().orElseThrow(() -> new SQLException("Failed to fetch users"));
        List<CarbonConsumption> allConsumptions = getAllConsumptions();

        Map<Integer, List<CarbonConsumption>> userConsumptions = allConsumptions.stream()
                .collect(Collectors.groupingBy(CarbonConsumption::getUserId));

        return allUsers.stream()
                .filter(user -> isUserInactive(user, userConsumptions.getOrDefault(user.getId(), List.of()), startDate, endDate))
                .collect(Collectors.toList());
    }

    private boolean isUserInactive(User user, List<CarbonConsumption> consumptions, LocalDate startDate, LocalDate endDate) {
        return consumptions.stream()
                .noneMatch(consumption ->
                        (consumption.getStartDate().isBefore(endDate) || consumption.getStartDate().isEqual(endDate)) &&
                                (consumption.getEndDate().isAfter(startDate) || consumption.getEndDate().isEqual(startDate))
                );
    }

}
