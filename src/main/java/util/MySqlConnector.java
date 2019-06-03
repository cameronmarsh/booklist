package util;

import model.BookParser;
import model.Parser;
import model.SingleColumnParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlConnector {
    static final String URL = "jdbc:mysql://localhost/books?serverTimezone=EST";
    static final String USER = "root";
    static final String PASSWORD = null;
    private String table;


    public MySqlConnector() {
    }

    public MySqlConnector(String table) {
        this.table = table;
    }

    public void setTable(String tableName) {
        this.table = tableName;
    }


    public void createBookTable(String tableName) throws SQLException, ClassNotFoundException {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (title varchar(100) NOT NULL, " +
                "author varchar(50), " +
                "published DATE," +
                "done BOOLEAN not null, " +
                "PRIMARY KEY (title))";

        update(sqlQuery);
    }

    public void removeBookTable(String tableName) throws SQLException, ClassNotFoundException {
        update("DROP table " + tableName + ";");
    }


    public void addBook(String title, String author, String datePublished, boolean read) throws SQLException, ClassNotFoundException {
        update("INSERT INTO " + this.table + "(title, author, published, done)" +
                " VALUES (\"" + title + "\",\"" + author + "\",\"" + datePublished + "\"," + read + ")");
    }


    public void removeBook(String title) throws SQLException, ClassNotFoundException {
        update("DELETE from " + this.table + " WHERE title = \"" + title + "\";");
    }


    public JSONObject getAllBooks() throws SQLException, ClassNotFoundException, JSONException {
        return select("*", "", new BookParser());
    }


    public JSONObject getTitles() throws SQLException, JSONException, ClassNotFoundException {
        return select("title", "", new SingleColumnParser("title"));
    }

    public JSONObject getReadBooks() throws SQLException, ClassNotFoundException {
        return select("*", "WHERE done = 1", new BookParser());
    }

    public JSONObject getUnreadBooks() throws SQLException, ClassNotFoundException {
        return select("*", "WHERE done = 0", new BookParser());
    }


    //TODO: refactor these to adhere to DRY
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


    private JSONObject query(String query, Parser parser) throws SQLException, ClassNotFoundException, JSONException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        JSONObject results = parser.parse(resultSet);

        statement.close();
        connection.close();

        return results;
    }


    private int update(String query) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        int result = statement.executeUpdate(query);

        statement.close();
        connection.close();

        return result;
    }


    private JSONObject select(String selection, String filter, Parser parser) throws SQLException, ClassNotFoundException, JSONException {
        if (this.table == null)
            return null;

        return query("select " + selection + " from " + this.table + " " + filter + ";", parser);
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        //Register JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Open connection to database
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
