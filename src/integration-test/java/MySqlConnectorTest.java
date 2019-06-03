import model.Book;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import util.MySqlConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MySqlConnectorTest {

    private MySqlConnector connector;
    private List<Book> testBooks = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        connector = new MySqlConnector();
        connector.createBookTable("dev");

        testBooks.add(new Book("Walden", "Henry David Thoreau", "1854-08-09", true));
        testBooks.add(new Book("The Sound and the Fury", "William Faulkner", "1929-04-21", false));

        connector.setTable("dev");
        for (Book book : testBooks) {
            connector.addBook(book.getTitle(), book.getAuthor(), book.getPublished(), book.isRead());
        }

    }

    @After
    public void tearDown() throws Exception {
        connector.removeBookTable("dev");
    }

    @Test
    public void returnsNullWhenTableNameIsNotSpecified() throws SQLException, ClassNotFoundException, JSONException {
        //don't set table name to anything --> stays null
        connector.setTable(null);
        JSONObject actual = connector.getAllBooks();

        assertNull(actual);
    }


    @Test
    public void canCreateBookTableInDatabase() throws SQLException, ClassNotFoundException {
        connector.createBookTable("bookTable");
        List<String> tables = connector.getTables();

        assertTrue(tables.contains("bookTable"));

        connector.removeBookTable("bookTable");
    }


    @Test
    public void canRemoveBookTableFromDatabase() throws SQLException, ClassNotFoundException {
        connector.createBookTable("tableToBeRemoved");
        int numTables = connector.getTables().size();

        connector.removeBookTable("tableToBeRemoved");

        assertEquals(connector.getTables().size(), numTables - 1);
        assertFalse(connector.getTables().contains("tableToBeRemoved"));

    }


    @Test
    public void canQueryBookDataFromDatabase() throws SQLException, ClassNotFoundException, JSONException {
        JSONArray expected = new JSONArray();
        for (int i = testBooks.size() - 1; i >= 0; i--) {
            JSONObject bookJson = new JSONObject();
            bookJson.put("title", testBooks.get(i).getTitle());
            bookJson.put("author", testBooks.get(i).getAuthor());
            bookJson.put("published", testBooks.get(i).getPublished());
            bookJson.put("read", testBooks.get(i).isRead());
            expected.put(bookJson);
        }

        JSONArray actual = connector.getAllBooks().getJSONArray("response");

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void canQuerySingleFieldFromDatabase() throws JSONException, SQLException, ClassNotFoundException {
        JSONArray expected = new JSONArray();
        for (int i = testBooks.size() - 1; i >= 0; i--) {
            JSONObject bookJson = new JSONObject();
            bookJson.put("title", testBooks.get(i).getTitle());
            expected.put(bookJson);
        }

        JSONArray actual = connector.getTitles().getJSONArray("response");

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void canQueryDatabaseWithFilter() throws SQLException, ClassNotFoundException {
        JSONArray expected = new JSONArray();
        JSONObject readBook = new JSONObject()
                .put("title", "Walden")
                .put("author", "Henry David Thoreau")
                .put("published", "1854-08-09")
                .put("read", true);
        expected.put(readBook);

        JSONArray actual = connector.getReadBooks().getJSONArray("response");

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }


    @Test
    public void canRemoveBookFromDatabase() throws SQLException, ClassNotFoundException {
        JSONArray expected = new JSONArray();
        JSONObject onlyBookInDb = testBooks.get(1).asJson();
        expected.put(onlyBookInDb);

        connector.removeBook("Walden");
        JSONArray remainingBooks = connector.getAllBooks().getJSONArray("response");

        JSONAssert.assertEquals(expected, remainingBooks, JSONCompareMode.NON_EXTENSIBLE);

    }
}
