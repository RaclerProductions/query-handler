package productions.racler.queryhandler;

import productions.racler.queryhandler.db.DatabaseConnection;
import productions.racler.queryhandler.db.Result;
import productions.racler.queryhandler.db.Row;
import productions.racler.queryhandler.db.settings.DatabaseSettings;

import java.sql.*;

/**
 * Created by Tim Stachorra 23.03.2017
 * <p>
 * A class for database queries.
 */
public class QueryHandler {

    private DatabaseSettings settings;
    private DatabaseConnection connection;

    /**
     * Set up the database settings
     *
     * @param settings the settings to connect with a database
     */
    public QueryHandler(DatabaseSettings settings) {
        this.settings = settings;
    }

    /**
     * Connect to the database
     */
    public void connect() {

        connection = new DatabaseConnection(settings);
    }

    /**
     * Close the connection to the database
     */
    public void close() {

        connection.close();
    }


    /**
     * Select a Result from the database
     *
     * @param sql       the query
     * @param parameter the parameter for the prepared statement
     * @return the result as Result
     */
    public Result select(String sql, Object... parameter) {

        try (Connection con = connection.getConnection()) {
            PreparedStatement preparedStatement = prepareStatement(con, sql, parameter);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSetToResult(resultSet);
        } catch (SQLException e) {
            if (settings.printExceptions()) {
                e.printStackTrace();
            }

            return null;
        }
    }

    /**
     * Execute a query
     *
     * @param sql       the query
     * @param parameter the parameter for the prepared statement
     * @return true if the query was successfully or false if not
     */
    public boolean execute(String sql, Object... parameter) {
        try (Connection con = connection.getConnection()) {
            PreparedStatement preparedStatement = prepareStatement(con, sql, parameter);
            preparedStatement.execute();
            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            if (settings.printExceptions()) {
                e.printStackTrace();
            }

            return false;
        }
    }


    /**
     * Parse the ResultSet to a Result HashMap
     *
     * @param rs the result set of a select query
     * @return the Result
     */
    private Result resultSetToResult(ResultSet rs) {

        Result resultList = new Result();

        try {

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                int numColumns = rsmd.getColumnCount();
                Row map = new Row();
                for (int i = 1; i < numColumns + 1; i++) {
                    String columnName = rsmd.getColumnName(i);
                    map.put(columnName, rs.getObject(columnName));
                }
                resultList.add(map);
            }

            return resultList;

        } catch (SQLException e) {
            if (settings.printExceptions()) {
                e.printStackTrace();
            }
        }

        return resultList;
    }


    /**
     * Prepare the statement and set the parameter
     *
     * @param con     the connection
     * @param sql     the sql query
     * @param objects the parameter
     * @return the prepared statements
     * @throws SQLException
     */
    private PreparedStatement prepareStatement(Connection con, String sql, Object[] objects) throws SQLException {

        PreparedStatement preparedStatement = con.prepareStatement(sql);

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof Integer) {
                preparedStatement.setInt(i + 1, (int) objects[i]);
            } else if (objects[i] instanceof String) {
                preparedStatement.setString(i + 1, (String) objects[i]);
            } else if (objects[i] instanceof Boolean) {
                preparedStatement.setBoolean(i + 1, (boolean) objects[i]);
            } else if (objects[i] instanceof Double) {
                preparedStatement.setDouble(i + 1, (double) objects[i]);
            } else if (objects[i] instanceof Timestamp) {
                preparedStatement.setTimestamp(i + 1, (Timestamp) objects[i]);
            } else if (objects[i] instanceof Long) {
                preparedStatement.setLong(i + 1, (long) objects[i]);
            } else if (objects[i] instanceof Float) {
                preparedStatement.setFloat(i + 1, (float) objects[i]);
            } else {
                preparedStatement.setObject(i + 1, objects[i]);
            }
        }

        return preparedStatement;
    }

}
