package Services;

import Domain.FoodDao;
import Domain.HousingDao;
import Domain.TransportDao;
import Domain.UserDAO;
import Entities.CarbonConsumption;
import Entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarbonImpactService {
    private FoodDao foodDao;
    private TransportDao transportDao;
    private HousingDao housingDao;
    private UserDAO userDao;

    public CarbonImpactService(FoodDao foodDao, TransportDao transportDao, HousingDao housingDao, UserDAO userDao) {
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


}
