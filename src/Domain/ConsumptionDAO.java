package Domain;

import Entities.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class ConsumptionDAO {
    private static final String CREATE_CONSUMPTION_QUERY = "INSERT INTO javaschema.consumption (amount, start_date, end_date, type, user_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_CONSUMPTION_BY_ID = "SELECT * FROM javaschema.consumption WHERE id = ?";
    private static final String DELETE_CONSUMPTION_SQL = "DELETE FROM javaschema.consumption WHERE id = ?";
    private static final String SELECT_ALL_CONSUMPTIONS_BY_USER_ID = "SELECT * FROM javaschema.consumption WHERE user_id = ?";
    private static final String UPDATE_CONSUMPTION_SQL = "UPDATE javaschema.consumption SET amount = ?, start_date = ?, end_date = ?, type = ?, user_id = ?  WHERE id = ?";


    public abstract Boolean save(CarbonConsumption consumption) throws SQLException ;
    public abstract Optional<CarbonConsumption> find(Integer id) throws SQLException ;
    public abstract Boolean update(CarbonConsumption consumption) throws SQLException ;
    public abstract Boolean delete(Integer id) throws SQLException ;
    public abstract Optional<List<CarbonConsumption> > findAll() throws SQLException ;
}
