package Handlers;

import DataClasses.Human;
import DataClasses.PersonReader;
import DataClasses.Student;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

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

    public void loadFromJSON(String table, String path) {
        try {
            Statement st = connection.createStatement();
            String operation = "create table " + table +
                    " ( id BIGSERIAL PRIMARY KEY , first_name VARCHAR (50), last_name VARCHAR(50), specialization VARCHAR(50), group_number INT, age INT)";
            st.executeUpdate(operation);
            System.out.println("Table created...");
            st.close();
        } catch (SQLException e) {
            System.out.println("Got SQL error");
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("Got JSON error");
            e.printStackTrace();
        }

        try {
            Statement st = connection.createStatement();
            ArrayList<Human> humans = PersonReader.processJSON(path);
            for (Human human : humans) {
                if (human instanceof Student) {
                    Student student = (Student) human;
                    String operation = "insert into " +
                            table +
                            " (first_name, last_name, specialization, group_number, age)" +
                            " values ( " +
                            wrapQuotes(student.getFirstName()) + ", " +
                            wrapQuotes(student.getLastName()) + ", " +
                            wrapQuotes(student.getSpecialization()) + ", " +
                            wrapQuotes(student.getGroupNumber().toString()) + ", " +
                            wrapQuotes(student.getAge().toString()) +
                            ");";
                    st.executeUpdate(operation);
                } else if (human instanceof Human) {
                    String operation = "insert into " +
                            table +
                            " (first_name, last_name, age)" +
                            " values ( " +
                            wrapQuotes(human.getFirstName()) + ", " +
                            wrapQuotes(human.getLastName()) + ", " +
                            wrapQuotes(human.getAge().toString()) +
                            ");";
                    st.executeUpdate(operation);
                }

            }
            st.close();
        } catch (SQLException e) {
            System.out.println("No records added");
            e.printStackTrace();
        }
    }

    public void loadFromJSON() {
        System.out.println("Input table name");
        String table = getUserInput();
        System.out.println("Input JSON path");
        String path = getUserInput();
        loadFromJSON(table, path);
    }

    public void printAllRecords(String table) {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + table);
            while (rs.next()) {
                System.out.println(rs.getString(2) + ' ' +
                        rs.getString(3) + ' ' +
                        rs.getString(4) + ' ' +
                        rs.getString(5) + ' ' +
                        rs.getString(6) + ' ');
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllRecords() {
        System.out.println("Input table name");
        String table = getUserInput();
        printAllRecords(table);
    }

    public boolean isConnected() {
        return connection != null;
    }

    public String getUserInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String string = null;
            while(true) {
                string = reader.readLine();
                if (string != null)
                    return string;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String wrapQuotes(String s) {
        return "'" + s + "'";
    }

}
