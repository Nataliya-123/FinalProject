package ActionWithTasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionCreator {

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "3edc#EDC3edc#EDC";
    private static final String DB_URL = "JDBC:mysql://localhost:3305/mysql";

    private static Connection connection = null;



    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
