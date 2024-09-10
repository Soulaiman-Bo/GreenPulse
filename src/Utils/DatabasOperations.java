package Utils;

import Config.DbConnection;
import Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public class DatabasOperations {

    public static void executeMutation(String sql, Consumer<PreparedStatement> parameterSetter, String successMessage, String errorMessage) {
        DbConnection dbConnection = null;
        Connection connection = null;

        try {
            dbConnection = DbConnection.getInstance();

            if (dbConnection != null) {
                connection = dbConnection.getConnection();

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    parameterSetter.accept(preparedStatement);
                    preparedStatement.execute();
                    ConsolePrinter.printSuccess(successMessage);
                }
            }

        } catch (SQLException e) {
            ConsolePrinter.printError(errorMessage);
            e.printStackTrace();
        } finally {
            if (dbConnection != null) {
                dbConnection.closeConnection();
            }
        }
    }

    public static  <T> T executeQuery(String sql, Consumer<PreparedStatement> parameterSetter, Function<ResultSet, T> resultMapper, String successMessage, String errorMessage) {
        DbConnection dbConnection = null;
        Connection connection = null;
        T result = null;

        try {
            dbConnection = DbConnection.getInstance();

            if (dbConnection != null) {
                connection = dbConnection.getConnection();

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    parameterSetter.accept(preparedStatement);
                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        result = resultMapper.apply(rs);
                    }
                    ConsolePrinter.printSuccess(successMessage);
                }
            }

        } catch (SQLException e) {
            ConsolePrinter.printError(errorMessage);
            e.printStackTrace();
        } finally {
            if (dbConnection != null) {
                dbConnection.closeConnection();
            }
        }
        return result;
    }

}
