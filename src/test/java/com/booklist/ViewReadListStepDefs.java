package com.booklist;

import cucumber.api.java8.En;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ViewReadListStepDefs implements En {
    private String response;

    public ViewReadListStepDefs() {
        Given("a list of books I've read$", () -> {

        });
        When("^I request the read list$", () -> {
            try(Scanner scanner = new Scanner(new java.net.URL("http://localhost:2345/read").openStream()).useDelimiter("\\A")) {
                response = scanner.hasNext() ? scanner.next() : "";
            }
        });
        Then("^information about those books is returned$", () -> {
            assertEquals("", response);
        });
    }
}
