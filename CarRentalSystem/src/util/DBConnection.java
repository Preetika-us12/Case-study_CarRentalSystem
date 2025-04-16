package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection = null;
    private static final String PROPERTY_FILE = "db.properties"; // Create this file

    public static Connection getConnection() {
        if (connection == null) {
            String connectionString = PropertyUtil.getPropertyString(PROPERTY_FILE);
            if (connectionString != null) {
                try {
                    // Load the JDBC driver (replace with your database's driver)
                    Class.forName("com.mysql.cj.jdbc.Driver"); // Example for MySQL
                    // For PostgreSQL: Class.forName("org.postgresql.Driver");
                    // For SQL Server: Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                    connection = DriverManager.getConnection(connectionString);
                    System.out.println("Database connection established.");
                } catch (ClassNotFoundException e) {
                    System.err.println("JDBC driver not found.");
                    e.printStackTrace();
                } catch (SQLException e) {
                    System.err.println("Failed to connect to the database.");
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
