package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookParser extends Parser {
    @Override
    public JSONObject parse(ResultSet resultSet) throws SQLException, JSONException {
        List<Book> bookResults = new ArrayList<>();

        while (resultSet.next()) {
            Book bookResult = new Book();
            bookResult.setTitle(resultSet.getString("title"));
            bookResult.setAuthor(resultSet.getString("author"));
            bookResult.setPublished(resultSet.getString("published"));
            bookResults.add(bookResult);
        }
        return toJsonResponse(bookResults);
    }
}
