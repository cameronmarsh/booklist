package util;

import java.sql.*;
import java.util.ArrayList;

public class MySqlConnector {
    static final String URL = "jdbc:mysql://localhost/test?serverTimezone=EST";
    static final String USER = "reader";
    static final String PASSWORD = "mysqlsux";

    public static String execute(String query){
        Connection conn;
        try {
            //Register JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open connection to database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            //execute query
            Statement statement = conn.createStatement();
            String req = query + " from test;";
            ResultSet res = statement.executeQuery("use books;" + req);

            //extract data from result set
            System.out.println(res.getClass());

            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load driver");

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
