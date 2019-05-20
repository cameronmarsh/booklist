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
            try(Scanner scanner = new Scanner(new java.net.URL("http://localhost:8769/titles").openStream()).useDelimiter("\\A")){
                response = scanner.hasNext() ? scanner.next() : "";
            }
        });

        String expectedResponse = "{\"response\":[{\"read\":false,\"author\":\"Henry David Thoreau\",\"published\":\"1854-08-09\",\"title\":\"Walden\"},{\"read\":false,\"author\":\"William Faulkner\",\"published\":\"1929-04-21\",\"title\":\"The Sound and the Fury\"},{\"read\":false,\"author\":\"Karl Ove Knausgaard\",\"published\":\"2009-01-01\",\"title\":\"My Struggle\"},{\"read\":false,\"author\":\"Marcel Proust\",\"published\":\"1913-01-01\",\"title\":\"In Search of Lost Time\"},{\"read\":false,\"author\":\"Alexandre Dumas\",\"published\":\"1844-08-24\",\"title\":\"The Count of Monte Cristo\"}]}";
        Then("^a list of all books saved is returned$", () -> {
            assertEquals(expectedResponse, response);
        });
    }
}
