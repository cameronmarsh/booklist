package com.booklist;

import cucumber.api.java8.En;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ViewAllTitlesStepDefs implements En {

    private String response;
    public ViewAllTitlesStepDefs() {
        Given("^the booklist server is alive and running$", () -> {

        });

        When("^a user requests the endpoint to see all titles$", () -> {
            try(Scanner scanner = new Scanner(new java.net.URL("http://localhost:8769/titles").openStream()).useDelimiter("\\A")){
                response = scanner.hasNext() ? scanner.next() : "";
            }
        });

        String expectedResponse = "{\"response\":[{\"title\":\"Walden\"},{\"title\":\"The Sound and the Fury\"},{\"title\":\"My Struggle\"},{\"title\": \"In Search of Lost Time\"},{\"title\":\"The Count of Monte Cristo\"}]}";
        Then("^a list of all books saved is returned$", () -> {
            JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.NON_EXTENSIBLE);
        });
    }
}
