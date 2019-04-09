import org.json.simple.JSONObject;

import java.util.ArrayList;

import static spark.Spark.*;

public class BooklistServer {

    public static void main(String[] args) {
        port(2333);
        get("/", (req, res) -> {
            JSONObject json = new JSONObject();
            json.put("greeting", "Welcome to your Booklist, John");

            return json.toJSONString();
        });
    }
}
