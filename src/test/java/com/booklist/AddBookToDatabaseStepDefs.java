package com.booklist;

import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import model.Book;
import util.HttpUtil;

import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class AddBookToDatabaseStepDefs implements En {

    private String response;

    public AddBookToDatabaseStepDefs() {
        Given("^the booklist server is running and there is a new book I want to save$", () -> {

        });

        When("^I send a POST request to the booklist server with the book's information$", () -> {
            Book bookToAdd = new Book("Game of Thrones", "George RR Martin", "1996-08-01", true);

            HttpUtil.addBook(bookToAdd);

            //get all the books
            try(Scanner scanner =  new Scanner(new java.net.URL("http://localhost:8769/books").openStream()).useDelimiter("\\A")) {
                response = scanner.hasNext() ? scanner.next() : "";
            }

            HttpUtil.removeBook("Game of Thrones");
        });

        Then("^the database adds a book entry to the database with that information$", () -> {
            JsonObject newBookJson = new JsonObject();
            newBookJson.addProperty("title", "Game of Thrones");
            newBookJson.addProperty("author", "George RR Martin");
            newBookJson.addProperty("published", "1996-08-01");
            newBookJson.addProperty("read", true);

            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            JsonArray arr = jsonResponse.get("response").getAsJsonArray();

            assertTrue(arr.contains(newBookJson));
        });
    }
}
