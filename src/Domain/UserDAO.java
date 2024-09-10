package Domain;

import Entities.User;
import Utils.ConsolePrinter;
import Utils.DatabasOperations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    private static final String CREATE_USER_QUERY = "INSERT INTO javaschema.users (full_name, email) VALUES (?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT id, full_name, email FROM javaschema.users WHERE id = ?";
    private static final String DELETE_USER_SQL = "DELETE FROM javaschema.users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM javaschema.users";
    private static final String UPDATE_USER_SQL = "UPDATE javaschema.users SET full_name = ?, email = ? WHERE id = ?";

    public void save(User user) {
        DatabasOperations.executeMutation(
                CREATE_USER_QUERY,
                preparedStatement -> {
                    try {
                        preparedStatement.setString(1, user.getFull_name());
                        preparedStatement.setString(2, user.getEmail());
                        ConsolePrinter.printInfo("Creating User...");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                },
                "User created successfully!",
                "Failed to create user!"
                );

    }

    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(DatabasOperations.executeQuery(
                SELECT_USER_BY_ID,
                preparedStatement -> {
                    try {
                        preparedStatement.setInt(1, id);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                },
                rs -> {
                    User user = null;
                    try {
                        if (rs.next()) {
                            String name = rs.getString("full_name");
                            String email = rs.getString("email");
                            user = new User(name, email, id);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                    return user;
                },
                "User Fetched successfully!",
                "Failed to fetch user!"
        ));
    }

    public void update(User user) {
        DatabasOperations.executeMutation(
                UPDATE_USER_SQL,
                preparedStatement -> {
                    try {
                        preparedStatement.setString(1, user.getFull_name());
                        preparedStatement.setString(2, user.getEmail());
                        preparedStatement.setInt(3, user.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                },
                "User Updated successfully!",
                "Failed to Update user!"
        );
    }

    public void delete(int id) {
        DatabasOperations.executeMutation(
                DELETE_USER_SQL,
                preparedStatement -> {
                    try {
                        preparedStatement.setInt(1, id);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                },
                "User with id " + id + " Deleted Successfully!",
                "Failed to delete user with id: " + id
        );
    }

    public Optional<List<User>> findAll() {
        return Optional.ofNullable(DatabasOperations.executeQuery(
                SELECT_ALL_USERS,
                preparedStatement -> {},
                rs -> {
                    List<User> users = new ArrayList<>();
                    try {
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String name = rs.getString("full_name");
                            String email = rs.getString("email");

                            users.add(new User(name, email, id));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return users.isEmpty() ? null : users;
                },
                "Users Fetched successfully!",
                "Failed to fetch users!"
        ));
    }

}
