package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    public static String getPropertyString(String propertyFileName) {
        Properties properties = new Properties();
        try (InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            if (inputStream == null) {
                System.out.println("Unable to find property file: " + propertyFileName);
                return null;
            }
            properties.load(inputStream);
            String host = properties.getProperty("db.host");
            String port = properties.getProperty("db.port");
            String database = properties.getProperty("db.name");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // Construct the connection string based on your database type
            String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database +
                    "?user=" + username + "&password=" + password;
            return connectionString;

            // Example for PostgreSQL:
            // return "jdbc:postgresql://" + host + ":" + port + "/" + database +
            //        "?user=" + username + "&password=" + password;

            // Example for SQL Server:
            // return "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + database +
            //        ";user=" + username + ";password=" + password;

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}