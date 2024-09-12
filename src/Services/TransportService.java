package Services;

import Domain.ConsumptionDAO;
import Domain.TransportDao;
import Entities.CarbonConsumption;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

    public class TransportService extends ConsumptionService {
    ConsumptionDAO consumptionDAO = new TransportDao();

    public Boolean save(CarbonConsumption consumption) {
        try {
            consumptionDAO.save(consumption);
            return Boolean.TRUE;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Boolean.FALSE;
        }
        // check if user exists
        // check if date already exists
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
    public Optional<List<CarbonConsumption>> findAll() throws SQLException {
        return consumptionDAO.findAll();
    }

    public Optional<List<CarbonConsumption>> findAllById(Integer id) throws SQLException {
            return consumptionDAO.findAllById(id);
    }
}
