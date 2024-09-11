package Domain;

import Entities.CarbonConsumption;
import Entities.Food;
import Entities.Transport;
import Entities.enums.FoodType;
import Entities.enums.TransportType;
import Utils.DatabasOperations;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransportDao extends ConsumptionDAO{
//    CarbonConsumption transportConsumption = new Transport(amount, distanceTravelled, TransportType.valueOf(transportType), LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter), id);

    private static final String CREATE_TRANSPORT_CONSUMPTION_QUERY = "INSERT INTO javaschema.transport (amount, start_date, end_date, type, user_id, distance, vehicule_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_TRANSPORT_CONSUMPTION_BY_ID = "SELECT * FROM javaschema.transport WHERE id = ?";
    private static final String SELECT_ALL_TRANSPORT_CONSUMPTIONS_BY_USER_ID = "SELECT * FROM javaschema.transport WHERE user_id = ?";
    private static final String SELECT_ALL_TRANSPORT_CONSUMPTIONS = "SELECT * FROM javaschema.transport";
    private static final String DELETE_TRANSPORT_CONSUMPTION_SQL = "DELETE FROM javaschema.transport WHERE id = ?";
    private static final String UPDATE_TRANSPORT_CONSUMPTION_SQL = "UPDATE javaschema.transport SET amount = ?, start_date = ?, end_date = ?, type = ?, user_id = ?, distance = ?, vehicule_type = ?  WHERE id = ?";


    @Override
    public Boolean save(CarbonConsumption consumption) throws SQLException {
        Transport food = (Transport) consumption;
            DatabasOperations.executeMutation(
                    CREATE_TRANSPORT_CONSUMPTION_QUERY,
                    preparedStatement -> {
                        try {
                            preparedStatement.setDate(2, Date.valueOf(food.getStartDate()));
                            preparedStatement.setDouble(1, food.getVolume());
                            preparedStatement.setDate(3, Date.valueOf(food.getEndDate()));
                            preparedStatement.setString(4, food.getConsumptionType().name());
                            preparedStatement.setInt(5, food.getUserId());
                            preparedStatement.setDouble(6, food.getDistance());
                            preparedStatement.setString(7, food.getTransportType().toString());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    },
                    "Transport Consumption created successfully!",
                    "Failed to create Transport Consumption!"
            );
        return Boolean.TRUE;
        }

    @Override
    public Optional<CarbonConsumption> find(Integer id) throws SQLException {
        return Optional.ofNullable(DatabasOperations.executeQuery(
                SELECT_TRANSPORT_CONSUMPTION_BY_ID,
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

                            String type = rs.getString("type");
                            Integer userId = rs.getInt("user_id");
                            LocalDate startDate = rs.getDate("start_date").toLocalDate();
                            LocalDate endDate = rs.getDate("end_date").toLocalDate();
                            String foodType = rs.getString("food_type");
                            Double weight = rs.getDouble("weight");
                            Double amount = rs.getDouble("amount");
                            String transportType = rs.getString("transport_type");
                            Double distance = rs.getDouble("distance");

                            consumption = new Transport(
                            amount,
                            distance,
                            TransportType.valueOf(transportType),
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
                "Transport  Fetched successfully!",
                "Failed to Transport user!"
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
    public Optional<List<CarbonConsumption>> findAll() throws SQLException {
        return Optional.ofNullable(DatabasOperations.executeQuery(
                SELECT_ALL_TRANSPORT_CONSUMPTIONS,
                preparedStatement -> {
                },
                rs -> {
                    List<CarbonConsumption> consumptions = new ArrayList<>();

                    try {
                        while (rs.next()) {
                            String type = rs.getString("type");
                            Integer id = rs.getInt("user_id");
                            LocalDate startDate = rs.getDate("start_date").toLocalDate();
                            LocalDate endDate = rs.getDate("end_date").toLocalDate();
                            Double amount = rs.getDouble("amount");
                            String transportType = rs.getString("vehicule_type");
                            Double distance = rs.getDouble("distance");

                            consumptions.add( new Transport(
                                    amount,
                                    distance,
                                    TransportType.valueOf(transportType),
                                    startDate,
                                    endDate,
                                    id));
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

    public Optional<List<CarbonConsumption>> findAllById(Integer userId) throws SQLException {
        return Optional.ofNullable(DatabasOperations.executeQuery(
                SELECT_ALL_TRANSPORT_CONSUMPTIONS_BY_USER_ID,
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
                            String type = rs.getString("type");
                            Integer id = rs.getInt("user_id");
                            LocalDate startDate = rs.getDate("start_date").toLocalDate();
                            LocalDate endDate = rs.getDate("end_date").toLocalDate();
                            Double amount = rs.getDouble("amount");
                            String transportType = rs.getString("vehicule_type");
                            Double distance = rs.getDouble("distance");

                            consumptions.add( new Transport(
                                    amount,
                                    distance,
                                    TransportType.valueOf(transportType),
                                    startDate,
                                    endDate,
                                    id));
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
