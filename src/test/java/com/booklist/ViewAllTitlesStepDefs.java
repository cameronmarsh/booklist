package com.booklist;

import cucumber.api.java8.En;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ViewAllTitlesStepDefs implements En {

    private String response;
    public ViewAllTitlesStepDefs() {
        Given("^the booklist server is alive and running$", () -> {

        });

        When("^a user requests the endpoint to see all titles$", () -> {
            try(Scanner scanner = new Scanner(new java.net.URL("http://localhost:2333/titles").openStream()).useDelimiter("\\A")){
                response = scanner.hasNext() ? scanner.next() : "";
            }
        });

        Then("^a list of all books saved is returned$", () -> {
            assertEquals("{\"response\": []}", response);
        });
    }
}
