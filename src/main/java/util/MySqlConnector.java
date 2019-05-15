package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySqlConnector {
    static final String URL = "jdbc:mysql://localhost/books?serverTimezone=EST";
    static final String USER = "root";
    static final String PASSWORD = "mysqlsux";

    public static List<String> execute(String query){
        Connection conn;
        ArrayList<String> results = new ArrayList<>();
        try {
            //Register JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open connection to database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            //execute query
            Statement statement = conn.createStatement();
            String req = query + " from test;";
            ResultSet res = statement.executeQuery(req);

            //extract data from result set
            while(res.next()){
                results.add(res.getString("title"));
            }

            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load driver");

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}
