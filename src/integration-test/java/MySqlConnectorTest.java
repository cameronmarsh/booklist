import model.Book;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import util.MySqlConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MySqlConnectorTest {

    @Before
    public void setUp() throws Exception {
       MySqlConnector connector = new MySqlConnector();
       connector.createBookTable("dev");
    }

    @Test
    @Ignore
    public void returnsNullWhenNoTableExists() throws SQLException, ClassNotFoundException {
        MySqlConnector connector = new MySqlConnector();
        connector.setTable("doesNotExist");

        List<Book> actual = connector.select("");

        assertNull(actual);
    }

    @Test
    @Ignore
    public void returnsNullWhenTableNameIsNotSpecified() throws SQLException, ClassNotFoundException {
        MySqlConnector connector = new MySqlConnector();
        //don't set table name to anything --> stays null

        List<Book> actual = connector.select("");

        assertNull(actual);
    }

    @Test
    @Ignore
    public void returnsEmptyListWithNoQuery() throws SQLException, ClassNotFoundException {
        MySqlConnector connector = new MySqlConnector();
        connector.setTable("dev");

        List<Book> actual = connector.select("blakj");
        List<Book> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

}
