package Domain;

import Entities.*;
import Entities.enums.FoodType;
import Utils.DatabasOperations;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FoodDao extends ConsumptionDAO{

    private static final String CREATE_FOOD_CONSUMPTION_QUERY = "INSERT INTO javaschema.food (amount, start_date, end_date, type, user_id, food_type, weight) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_FOOD_CONSUMPTION_BY_ID = "SELECT * FROM javaschema.food WHERE id = ?";
    private static final String SELECT_ALL_FOOD_CONSUMPTIONS_BY_USER_ID = "SELECT * FROM javaschema.food WHERE user_id = ?";
    private static final String DELETE_FOOD_CONSUMPTION_SQL = "DELETE FROM javaschema.food WHERE id = ?";
    private static final String UPDATE_FOOD_CONSUMPTION_SQL = "UPDATE javaschema.food SET amount = ?, start_date = ?, end_date = ?, type = ?, user_id = ?  WHERE id = ?";


    @Override
    public Boolean save(CarbonConsumption consumption) throws SQLException {
        Food food = (Food) consumption;
            DatabasOperations.executeMutation(
                    CREATE_FOOD_CONSUMPTION_QUERY,
                    preparedStatement -> {
                        try {
                            preparedStatement.setDate(2, Date.valueOf(food.getStartDate()));
                            preparedStatement.setDouble(1, food.getVolume());
                            preparedStatement.setDate(3, Date.valueOf(food.getEndDate()));
                            preparedStatement.setString(4, food.getConsumptionType().name());
                            preparedStatement.setInt(5, food.getUserId());
                            preparedStatement.setString(6, food.getFoodType().toString());
                            preparedStatement.setDouble(7, food.getWeight());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    },
                    "Consumption created successfully!",
                    "Failed to create Consumption!"
            );
        return Boolean.TRUE;
        }

    @Override
    public Optional<CarbonConsumption> find(Integer id) throws SQLException {
        return Optional.ofNullable(DatabasOperations.executeQuery(
                SELECT_FOOD_CONSUMPTION_BY_ID,
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

                            LocalDate startDate = rs.getDate("start_date").toLocalDate();
                            LocalDate endDate = rs.getDate("end_date").toLocalDate();
                            String type = rs.getString("type");
                            Integer userId = rs.getInt("user_id");
                            String foodType = rs.getString("food_type");
                            Double weight = rs.getDouble("weight");
                            Double amount = rs.getDouble("amount");

                            consumption = new Food(amount, FoodType.valueOf(foodType), weight , startDate, endDate, userId);

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                    return consumption;
                },
                "User Fetched successfully!",
                "Failed to fetch user!"
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
    public List<CarbonConsumption> findAll(Integer userId) throws SQLException {
        return DatabasOperations.executeQuery(
                SELECT_ALL_FOOD_CONSUMPTIONS_BY_USER_ID,
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
                            int id = rs.getInt("id");
                            // ConsumptionType consumptionType = ConsumptionType.valueOf(rs.getString("type"));
                            Double amount = rs.getDouble("amount");
                            LocalDate start_date = rs.getDate("start_date").toLocalDate();
                            LocalDate end_date = rs.getDate("end_date").toLocalDate();
                            String foodType = rs.getString("food_type");
                            Double weight = rs.getDouble("weight");
                            int user_id = rs.getInt("user_id");

                            consumptions.add(new Food(amount, FoodType.valueOf(foodType), weight , start_date, end_date, user_id));

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return consumptions;
                },
                "Consumptions Fetched successfully!",
                "Failed to fetch Consumptions!"
        );
    }


}
