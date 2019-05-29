import org.json.simple.JSONObject;
import util.MySqlConnector;

import static spark.Spark.*;

public class BooklistServer {
    public static void main(String[] args) {
        MySqlConnector connector = new MySqlConnector();
        connector.setTable("test");

        port(8769);
        get("/", (req, res) -> {
            JSONObject json = new JSONObject();
            json.put("response", "Welcome to Booklist!");

            return json.toJSONString();
        });

        get("/books", (req, res) -> connector.getAllBooks());

        get("/titles", (req, res) -> connector.getTitles());

    }
}
