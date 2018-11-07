package com.hacaller.jdatabase;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Herbert Caller on 07/11/2018.
 */
public final class CarDatabaseUtils {

    static String databaseLocation = "jdbc:sqlite:C:/sqlite/db/CarApp.db";

    public static Connection connect(String databaseLocation) {

        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(databaseLocation);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createCarDatabase(String databaseLocation) {

        try (Connection conn = DriverManager.getConnection(databaseLocation)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                ResultSet res = meta.getTables(null, null, "cars", new String[] {"TABLE"});
                if (!res.next()){
                    createCarTable(databaseLocation);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createCarTable(String databaseLocation) {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS cars (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	logo text NOT NULL,\n"
                + "	brand text NOT NULL,\n"
                + "	website text NOT NULL,\n"
                + "	founded_year integer NOT NULL,\n"
                + "	car_photo text NOT NULL,\n"
                + "	rating integer\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(databaseLocation);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void deleteCarTable(String databaseLocation){

        // SQL statement for deleting an existing table
        String sql = "DROP TABLE IF EXISTS cars;";

        try (Connection conn = DriverManager.getConnection(databaseLocation);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
