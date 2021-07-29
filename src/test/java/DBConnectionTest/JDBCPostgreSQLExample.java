package DBConnectionTest;

import java.sql.*;

public class JDBCPostgreSQLExample {

    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/javatest";
    static final String USER = "javauser";
    static final String PASS = "javauser";

    public static void main(String[] argv) {

        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

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
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM students");
                while (rs.next()) {
                    System.out.println(rs.getString(1) + ' ' +
                            rs.getString(2) + ' ' +
                            rs.getString(3) + ' ' +
                            rs.getString(4) + ' ' +
                            rs.getString(5) + ' ');
                }
                rs.close();
                st.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to make connection to database");
        }
    }
}