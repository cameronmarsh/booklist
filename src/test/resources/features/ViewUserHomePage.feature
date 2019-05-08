Feature: View specific user home page
    As a user, I want to see a home page specific to my account after logging in

    Scenario: Navigating to custom home page
    Given the booklist server is up and running
    When a user successfully logs into the site
    Then the home page for that user is shown