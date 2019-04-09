import org.json.simple.JSONObject;

import static spark.Spark.*;

public class BooklistServer {

    public static void main(String[] args) {
        port(2333);
        get("/", (req, res) -> {
            String html = "<a href=\'http://localhost:2333/read\'>Read Books</a>\n";
            html += "<a href=\'http://localhost:2333/unread\'>Unread Books</a>\n";

            return html;
        });
    }
}
