package com.booklist;

import cucumber.api.java8.En;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.Scanner;

public class ViewAllUnreadStepDefs implements En {
    private String response;

    public ViewAllUnreadStepDefs() {
        Given("^the booklist server has books marked unread$", () -> {

        });

        When("^a user requests the endpoint for unread books$", () -> {
            try(Scanner scanner =  new Scanner(new java.net.URL("http://localhost:8769/books/unread").openStream()).useDelimiter("\\A")) {
                response = scanner.hasNext() ? scanner.next() : "";
            }
        });

        JSONArray unreadBooks = new JSONArray();
        unreadBooks.put(new JSONObject()
                .put("title", "In Search of Lost Time")
                .put("author", "Marcel Proust")
                .put("published", "1890-06-24")
                .put("read", false)
        );
        unreadBooks.put(new JSONObject()
                .put("title", "My Struggle")
                .put("author", "Karl Ove Knausgaard")
                .put("published", "2009-10-20")
                .put("read", false)
        );
        unreadBooks.put(new JSONObject()
                .put("title", "The Sound and the Fury")
                .put("author", "William Faulkner")
                .put("published", "1968-04-24")
                .put("read", false)
        );

        JSONObject expected = new JSONObject();
        expected.put("response", unreadBooks);
        Then("^a list of books marked unread is returned$", () -> {
            JSONAssert.assertEquals(response, expected, JSONCompareMode.NON_EXTENSIBLE);
        });

    }
}
