package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Parser {
    public abstract JSONObject parse(ResultSet resultSet) throws SQLException, JSONException;

    JSONObject toJsonResponse(List<Book> bookResults) throws JSONException {
        JSONObject response = new JSONObject();
        JSONArray results = new JSONArray();

        for(Book book : bookResults){
            results.put(book.asJson());
        }

        response.put("response", results);

        return response;
    }


    JSONObject toJsonResponse(String columnTitle, List<String> resultSet) throws JSONException {
        JSONObject response = new JSONObject();
        JSONArray results = new JSONArray();

        for(String result : resultSet){
            JSONObject columnResult = new JSONObject();
            columnResult.put(columnTitle, result);
            results.put(columnResult);
        }

        response.put("response", results);

        return response;
    }
}

