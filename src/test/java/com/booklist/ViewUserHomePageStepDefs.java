package com.booklist;

import cucumber.api.java8.En;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ViewUserHomePageStepDefs implements En {

    private String response;

    public ViewUserHomePageStepDefs() {
        Given("the booklist server is up and running$", () -> {

        });

        When("^a user successfully logs into the site$", () -> {
            try(Scanner scanner = new Scanner(new java.net.URL("http://localhost:2333/user?id=3").openStream()).useDelimiter("\\A")){
                response = scanner.hasNext() ? scanner.next() : "";
            }
        });

        Then("^the home page for that user is shown$", () -> {
            assertEquals("{\"greeting\":\"Welcome to Booklist, Korka!\"}", response);
        });
    }
}
