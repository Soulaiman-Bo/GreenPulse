package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnection {

    private static DbConnection instance;
    private final Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/javapostgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private DbConnection() throws SQLException {
        Connection tempConnection = null;
        try {
            tempConnection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connection = tempConnection;
    }

    public static DbConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

