import org.json.simple.JSONObject;
import util.MySqlConnector;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class BooklistServer {
    public static void main(String[] args) {
        port(2333);
        get("/", (req, res) -> {
            JSONObject json = new JSONObject();
            json.put("response", "Welcome to Booklist!");

            return json.toJSONString();
        });

        get("/titles", (req, res) -> MySqlConnector.execute("select title"));

    }
}
