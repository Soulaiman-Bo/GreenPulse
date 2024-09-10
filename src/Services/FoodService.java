package Services;

import Domain.ConsumptionDAO;
import Domain.FoodDao;
import Entities.CarbonConsumption;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FoodService extends ConsumptionService {
    ConsumptionDAO consumptionDAO = new FoodDao();

    public Boolean save(CarbonConsumption consumption) {

        try {
            consumptionDAO.save(consumption);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // check if user exists
        // check if date already exists
        return Boolean.TRUE;
    }

    @Override
    public Optional<CarbonConsumption> find(Integer id) throws SQLException {
        return consumptionDAO.find(id);
    }

    @Override
    public Boolean update(CarbonConsumption consumption) throws SQLException {
        return null;
    } // Not Implemented

    @Override
    public Boolean delete(Integer user) throws SQLException {
        return null;
    } // Not Implemented

    @Override
    public List<CarbonConsumption> findAll(Integer userId) throws SQLException {
        List<CarbonConsumption> consumption =  consumptionDAO.findAll(10);
        return consumption;
    }
}
