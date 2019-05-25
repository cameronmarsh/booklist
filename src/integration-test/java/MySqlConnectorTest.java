import junit.framework.TestCase;
import model.Book;
import org.dbunit.DBTestCase;
import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.assertion.DbUnitAssert;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import util.MySqlConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class MySqlConnectorTest {

    private MySqlConnector connector;
    private List<Book> testBooks = new ArrayList<>();


    @Ignore
    public void returnsNullWhenNoTableExists() throws SQLException, ClassNotFoundException {
        connector.setTable("doesNotExist");
        List<Book> actual = connector.select("");

        assertNull(actual);
    }

    @Test
    public void returnsNullWhenTableNameIsNotSpecified() throws SQLException, ClassNotFoundException {
        //don't set table name to anything --> stays null
        connector.setTable(null);
        List<Book> actual = connector.select("");

        assertNull(actual);
    }

    @Test
    @Ignore
    public void returnsEmptyListWithNoQuery() throws SQLException, ClassNotFoundException {
        connector.setTable("dev");

        List<Book> actual = connector.select("blakj");
        List<Book> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

}
