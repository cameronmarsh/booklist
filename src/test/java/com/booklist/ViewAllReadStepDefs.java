package com.booklist;

import cucumber.api.java8.En;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.Scanner;

public class ViewAllReadStepDefs implements En {
    private String response;

    public ViewAllReadStepDefs() {
        Given("^the booklist server has books marked as read$", () -> {

        });

        When("^a user requests the endpoint to view read books$", () -> {
            try(Scanner scanner =  new Scanner(new java.net.URL("http://localhost:8769/books/read").openStream()).useDelimiter("\\A")) {
                response = scanner.hasNext() ? scanner.next() : "";
            }
        });

        JSONArray readBooks = new JSONArray();
        readBooks.put(new JSONObject()
                .put("title", "Walden")
                .put("author", "Henry David Thoreau")
                .put("published", "1899-12-04")
                .put("read", true)
        );
        readBooks.put(new JSONObject()
                .put("title", "The Count of Monte Cristo")
                .put("author", "Alexandre Dumas")
                .put("published", "1906-03-28")
                .put("read", true)
        );

        JSONObject expected = new JSONObject();
        expected.put("response", readBooks);

        Then("^a list of books marked as read is returned$", () -> {
            JSONAssert.assertEquals(response, expected, JSONCompareMode.NON_EXTENSIBLE);
        });
    }
}
