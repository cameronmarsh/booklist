package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingleColumnParser extends Parser {
    private String columnName;

    public SingleColumnParser(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public JSONObject parse(ResultSet resultSet) throws SQLException, JSONException {
            List<String> results = new ArrayList<>();

            while(resultSet.next()){
                String result = resultSet.getString(1);
                results.add(result);
            }

            return toJsonResponse(columnName, results);
    }
}
