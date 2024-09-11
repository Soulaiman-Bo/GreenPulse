package Domain;

import Entities.CarbonConsumption;
import Entities.Housing;
import Entities.Transport;
import Entities.User;
import Entities.enums.HousingType;
import Entities.enums.TransportType;
import Utils.DatabasOperations;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HousingDao extends ConsumptionDAO{

    private static final String CREATE_HOUSING_CONSUMPTION_QUERY = "INSERT INTO javaschema.housing (amount, start_date, end_date, type, user_id, energy_type, energy) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_HOUSING_CONSUMPTION_BY_ID = "SELECT * FROM javaschema.housing WHERE id = ?";
    private static final String SELECT_ALL_HOUSING_CONSUMPTIONS_BY_USER_ID = "SELECT * FROM housing.transport WHERE user_id = ?";
    private static final String DELETE_HOUSING_CONSUMPTION_SQL = "DELETE FROM javaschema.housing WHERE id = ?";
    private static final String UPDATE_HOUSING_CONSUMPTION_SQL = "UPDATE javaschema.housing SET amount = ?, start_date = ?, end_date = ?, type = ?, user_id = ?, distance = ?, vehicule_type = ?  WHERE id = ?";


    @Override
    public Boolean save(CarbonConsumption consumption) throws SQLException {
        Housing housing = (Housing) consumption;
            DatabasOperations.executeMutation(
                    CREATE_HOUSING_CONSUMPTION_QUERY,
                    preparedStatement -> {
                        try {
                            preparedStatement.setDouble(1, housing.getVolume());
                            preparedStatement.setDate(2, Date.valueOf(housing.getStartDate()));
                            preparedStatement.setDate(3, Date.valueOf(housing.getEndDate()));
                            preparedStatement.setString(4, housing.getConsumptionType().name());
                            preparedStatement.setInt(5, housing.getUserId());
                            preparedStatement.setDouble(7, housing.getEnergyConsumption());
                            preparedStatement.setString(6, housing.getHousingType().toString());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    },
                    "Housing Consumption created successfully!",
                    "Failed to create Housing Consumption!"
            );
        return Boolean.TRUE;
        }

    @Override
    public Optional<CarbonConsumption> find(Integer id) throws SQLException {
        return Optional.ofNullable(DatabasOperations.executeQuery(
                SELECT_HOUSING_CONSUMPTION_BY_ID,
                preparedStatement -> {
                    try {
                        preparedStatement.setInt(1, id);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                },
                rs -> {
                    CarbonConsumption consumption = null;
                    try {
                        if (rs.next()) {

                            Integer userId = rs.getInt("user_id");
                            String type = rs.getString("type");
                            LocalDate startDate = rs.getDate("start_date").toLocalDate();
                            LocalDate endDate = rs.getDate("end_date").toLocalDate();
                            String foodType = rs.getString("food_type");
                            Double weight = rs.getDouble("weight");
                            Double amount = rs.getDouble("amount");
                            String transportType = rs.getString("transport_type");
                            Double energy = rs.getDouble("energy");

                            consumption = new Housing(
                                    amount,
                                    energy,
                                    HousingType.valueOf(transportType),
                                     startDate,
                                     endDate,
                                     userId);

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                    return consumption;
                },
                "Housing  Fetched successfully!",
                "Failed to find Housing!"
        ));
    }

    @Override
    public Boolean update(CarbonConsumption consumption) throws SQLException {
        return null;
    } // Not Implemented

    @Override
    public Boolean delete(Integer id) throws SQLException {
        return Boolean.TRUE;
    } // Not Implemented

    @Override
    public Optional<List<CarbonConsumption>>  findAll(Integer userId) throws SQLException {
        return Optional.ofNullable(DatabasOperations.executeQuery(
                SELECT_ALL_HOUSING_CONSUMPTIONS_BY_USER_ID,
                preparedStatement -> {
                    try {
                        preparedStatement.setInt(1, userId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                },
                rs -> {
                    List<CarbonConsumption> consumptions = new ArrayList<>();

                    try {
                        while (rs.next()) {

                            Integer id = rs.getInt("user_id");
                            String type = rs.getString("type");
                            LocalDate startDate = rs.getDate("start_date").toLocalDate();
                            LocalDate endDate = rs.getDate("end_date").toLocalDate();
                            String foodType = rs.getString("food_type");
                            Double weight = rs.getDouble("weight");
                            Double amount = rs.getDouble("amount");
                            String transportType = rs.getString("transport_type");
                            Double energy = rs.getDouble("energy");

                            consumptions.add(  new Housing(
                                    amount,
                                    energy,
                                    HousingType.valueOf(transportType),
                                    startDate,
                                    endDate,
                                    userId));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return consumptions.isEmpty() ? null : consumptions;
                },
                "Consumptions Fetched successfully!",
                "Failed to fetch Consumptions!"
        ));
    }
}
