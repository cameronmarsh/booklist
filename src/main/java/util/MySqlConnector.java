package util;

import model.Book;
import model.BookParser;
import model.Parser;
import model.SingleColumnParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static String getConnectionURL() {
        return URL;
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        //Register JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Open connection to database
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private JSONObject query(String query, Parser parser) throws SQLException, ClassNotFoundException, JSONException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        JSONObject results = parser.parse(resultSet);

        statement.close();
        connection.close();

        return results;
    }






    public int update(String query) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        int result = statement.executeUpdate(query);

        statement.close();
        connection.close();

        return result;
    }


    private JSONObject select(String selection, Parser parser) throws SQLException, ClassNotFoundException, JSONException {
        if (this.table == null)
            return null;

        return query("select " + selection + " from " + this.table + ";", parser);
    }


    private static String getJsonResponse(List<Book> results) throws JSONException {
        JSONArray books = new JSONArray();
        for (Book book : results) {
            books.put(book.asJson());
        }

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("response", books);
        return jsonResponse.toString();
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

    public void removeBookTable(String tableName) throws SQLException, ClassNotFoundException {
        update("DROP table " + tableName + ";");
    }

    public void addBook(String title, String author, String datePublished, boolean read) throws SQLException, ClassNotFoundException {
        update("INSERT INTO " + this.table + "(title, author, published)" + //, read)" +
                " VALUES (\"" + title + "\",\"" + author + "\",\"" + datePublished + "\")"); //+ "," + read + ")");
        //TODO: add read bool to table
    }

    //TODO: refactor
    public List<String> getTables() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        List<String> tables = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery("show tables;");
        while(resultSet.next()){
            tables.add(resultSet.getString(1));
        }

        return tables;

    }

    public JSONObject getAllBooks() throws SQLException, ClassNotFoundException, JSONException {
        return select("*", new BookParser());
    }

    public JSONObject getTitles() throws SQLException, JSONException, ClassNotFoundException {
        return select("title", new SingleColumnParser("title"));
    }
}
