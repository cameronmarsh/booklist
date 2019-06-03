import org.json.JSONObject;
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

            return json;
        });

        get("/books", (req, res) -> connector.getAllBooks());
        get("/books/read", (req, res) -> connector.getReadBooks());
        get("/books/unread", (req, res) -> connector.getUnreadBooks());
        get("/titles", (req, res) -> connector.getTitles());

        post("/books/add", (req, res) -> {
            connector.addBook(
                    req.queryParams("title"),
                    req.queryParams("author"),
                    req.queryParams("published"),
                    Boolean.parseBoolean(req.queryParams("read"))
            );

           res.redirect("/books");
           return connector.getAllBooks();
        });

        post("/books/remove", (req, res) -> {
            connector.removeBook(req.queryParams("title"));

            res.redirect("/books");
            return connector.getAllBooks();
        });

        post("/books/markRead", (req, res) -> {
            connector.markAsRead(req.queryParams("title"));

            res.redirect("/books");
            return connector.getAllBooks();
        });

    }
}
