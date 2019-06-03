package util;

import model.Book;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static int addBook(Book book) throws IOException {
        String url = "http://localhost:8769/books/add";

        String urlParams = String.format("title=%s&author=%s&published=%s&read=%s",
                book.getTitle(),
                book.getAuthor(),
                book.getPublished(),
                book.isRead()
        );

        return post(url, urlParams);
    }


    public static int removeBook(String title) throws IOException {
        String url = "http://localhost:8769/books/remove";

        String urlParams = "title=" + title;

        return post(url, urlParams);
    }

    private static int post(String url, String urlParams) throws IOException {
        URL postUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) postUrl.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        conn.setRequestProperty("Accept-Language", "en-US;q=0.5");

        conn.setDoOutput(true);
        DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
        writer.writeBytes(urlParams);
        writer.flush();
        writer.close();

        return conn.getResponseCode();
    }


}
