package com.booklist;

import cucumber.api.java8.En;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ViewHomePageStepDefs implements En {

    private String response;

    public ViewHomePageStepDefs() {
        Given("the booklist server is running$", () -> {

        });

        When("^a user requests the site$", () -> {
            try(Scanner scanner = new Scanner(new java.net.URL("http://localhost:2333/").openStream()).useDelimiter("\\A")) {
                response = scanner.hasNext() ? scanner.next() : "";
            }
        });

        String expectedResponse = "{\"response\":\"Welcome to Booklist!\"}";

        Then("^that user is redirected to their read list page$", () -> {
            assertEquals(expectedResponse, response);
        });
    }
}
