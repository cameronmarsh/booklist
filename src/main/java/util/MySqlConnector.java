package util;

import model.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlConnector {
    static final String URL = "jdbc:mysql://localhost/books?serverTimezone=EST";
    static final String USER = "reader";
    static final String PASSWORD = "mysqlsux";
    private String table;


    public MySqlConnector() {
    }

    public MySqlConnector(String table) {
        this.table = table;
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        //Register JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Open connection to database
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private ResultSet query(String query) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        statement.close();
        connection.close();

        return resultSet;
    }


    public int update(String statment) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        int result = statement.executeUpdate(statment);

        statement.close();
        connection.close();

        return result;
    }


    public List<Book> select(String selection) throws SQLException, ClassNotFoundException {
        if (this.table == null)
            return null;

        ArrayList<Book> results = new ArrayList<>();
        ResultSet res = query("select " + selection + " from " + this.table + ";");

        //extract data from result set
        while (res.next()) {
            Book book = new Book();
            book.setTitle(res.getString("title"));
            book.setAuthor(res.getString("author"));
            book.setPublished(res.getDate("published"));
            book.setRead(res.getBoolean("read"));

            results.add(book);
        }

        return results;
    }


    private static String getJsonResponse(List<Book> results) {
        JSONArray books = new JSONArray();
        for (Book book : results) {
            books.add(book.asJson());
        }

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("response", books);
        return jsonResponse.toJSONString();
    }

    public void setTable(String tableName) {
        this.table = tableName;
    }


    public void createBookTable(String tableName) throws SQLException, ClassNotFoundException {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (title varchar(100) NOT NULL, " +
                "author varchar(50), " +
                "published DATE," +
//                "read BOOLEAN, " +
                "PRIMARY KEY (title))";

        update(sqlQuery);
    }
}
