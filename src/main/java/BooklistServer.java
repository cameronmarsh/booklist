import org.json.simple.JSONObject;

import java.util.ArrayList;

import static spark.Spark.*;

public class BooklistServer {

    public static void main(String[] args) {
        port(2333);
        get("/", (req, res) -> {
            JSONObject json = new JSONObject();
            json.put("readList", new ArrayList<>());
            json.put("unreadList", new ArrayList<>());

            return json.toJSONString();
        });
    }
}
