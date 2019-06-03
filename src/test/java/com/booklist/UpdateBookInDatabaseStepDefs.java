package com.booklist;

import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import model.Book;
import util.HttpUtil;

import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class UpdateBookInDatabaseStepDefs implements En {
    private String response;

    public UpdateBookInDatabaseStepDefs() {
        Given("^a book in the database is unread$", () -> {
        });

        When("^I read the book and send a post request to mark the book as read$", () -> {
            HttpUtil.addBook(new Book("Nausea", "Jean-Paul Sartre", "1938-03-05", false));
            HttpUtil.markAsRead("Nausea");

            Scanner scanner = new Scanner(new java.net.URL("http://localhost:8769/books").openStream()).useDelimiter("\\A");
            response = scanner.hasNext() ? scanner.next() : "";

            HttpUtil.removeBook("Nausea");
        });

        Then("^the read field in the database is updated to true$", () -> {
            JsonObject nausea = new JsonObject();
            nausea.addProperty("title", "Nausea");
            nausea.addProperty("author", "Jean-Paul Sartre");
            nausea.addProperty("published", "1938-03-05");
            nausea.addProperty("read", true);

            Gson gson = new Gson();
            JsonArray allBooks = (JsonArray) gson.fromJson(response, JsonObject.class).get("response");

            assertTrue(allBooks.contains(nausea));
        });
    }
}
