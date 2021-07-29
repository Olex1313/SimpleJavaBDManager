package Handlers;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseManager {
    private static Connection connection;

    public DataBaseManager(String DB_URL, String USER, String PASS) {
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
            this.connection = connection;
        } else {
            System.out.println("Failed to make connection to database");
            this.connection = null;
        }
    }

    public void getJSONTable(String table, String path) {

    }

}
