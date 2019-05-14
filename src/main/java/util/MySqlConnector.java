package util;

import java.sql.*;

public class MySqlConnector {
    static final String URL = "jdbc:mysql://localhost/test?serverTimezone=EST";
    static final String USER = "reader";
    static final String PASSWORD = "mysqlsux";

    public static String execute(String query){
        Connection conn;
        StringBuilder result = new StringBuilder();
        try {
            //Register JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open connection to database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            //execute query
            Statement statement = conn.createStatement();
            String req = "select title from test";
            ResultSet res = statement.executeQuery(req);

            //extract data from result set
            while(res.next()){
                //TODO: add result to string builder
            }

            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load driver");

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
