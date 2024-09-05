package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection instance;
    private final Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/company_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    private DbConnection() throws SQLException {
        try {
            this.connection  =  DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("PostgreSQL Driver not found!", e);
        }

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
