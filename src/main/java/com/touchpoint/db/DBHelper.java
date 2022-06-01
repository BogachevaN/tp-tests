package com.touchpoint.db;

import com.touchpoint.environment.PropertyManager;
import com.touchpoint.logging.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.touchpoint.environment.PropertyManager.*;

public final class DBHelper {
    private static final String URL = getProperty("dataBase.url");
    private static final String USER_NAME = getProperty("dataBase.userName");
    private static final String PASSWORD = getProperty("dataBase.password");

    private static final String LOG_MESSAGE = "Request executed [%s]";
    private static final String ERROR_LOG_MESSAGE = "Exception during execution query";


    private DBHelper() {
        throw new UnsupportedOperationException(String.format("Creating an instance of the %s class is forbidden",
                PropertyManager.class));
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
                connection = DriverManager
                        .getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            Log.error("Error during connection the database", e);
        }
        return connection;

    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            Log.error("Error during connection the database", e);
        }
    }

    public void executeQuery(String query) {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            Log.info(String.format(LOG_MESSAGE, query));
            ps.executeUpdate();
        } catch (SQLException e) {
            Log.error(ERROR_LOG_MESSAGE, e);
        } finally {
            closeConnection(connection);
        }
    }

    public static String getSingleResult(String query, String tableColumnName) {
        List<String> returnValue = collectValueFromSQLRequestToList(query, tableColumnName);
        return returnValue.get(0);
    }

    public static List<String> collectValueFromSQLRequestToList(String query, String tableColumnName) {
        List<String> returnValue = new ArrayList<>();
        List<Map<String, String>> mapResults = collectValueFromSQLRequestToMaps(query);
        mapResults.forEach(map -> returnValue.add(map.get(tableColumnName)));
        return returnValue;
    }

    private static List<Map<String, String>> collectValueFromSQLRequestToMaps(String sqlQuery) {
        List<Map<String, String>> results = new ArrayList<>();
        Connection connection = getConnection();
        Log.info(String.format(LOG_MESSAGE, sqlQuery));
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                Map<String, String> resultsTmp = new HashMap<>();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int numColumns = resultSetMetaData.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    resultsTmp.put(resultSetMetaData.getColumnName(i),
                            resultSet.getString(resultSetMetaData.getColumnLabel(i)));
                }
                results.add(resultsTmp);
            }
        } catch (SQLException e) {
            Log.error(ERROR_LOG_MESSAGE, e);
        }
        return results;
    }
}
