package util;

import model.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySqlConnector {
    static final String URL = "jdbc:mysql://localhost/books?serverTimezone=EST";
    static final String USER = "reader";
    static final String PASSWORD = "mysqlsux";

    public static String execute(){
        Connection conn;
        ArrayList<Book> results = new ArrayList<>();
        try {
            //Register JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open connection to database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            //execute query
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select * from test;");

            //extract data from result set
            while(res.next()){
                Book book = new Book();
                book.setTitle(res.getString("title"));
                book.setAuthor(res.getString("author"));
                book.setPublished(res.getDate("published"));
//                book.setRead(res.getBoolean("read"));

                results.add(book);
            }

            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load driver");

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getJsonResponse(results);
    }


    private static String getJsonResponse(List<Book> results){
        JSONArray books = new JSONArray();
        for(Book book : results){
            books.add(book.asJson());
        }

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("response", books);
        return jsonResponse.toJSONString();
    }
}
