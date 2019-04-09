import static spark.Spark.*;

public class BooklistServer {

    public static void main(String[] args) {
        port(2333);
        get("/", (req, res) -> "[]");

    }
}
