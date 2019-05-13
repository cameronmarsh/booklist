Feature: View home page
    As a potential user, I want to navigate to the book list application site
    
Scenario: Navigating to site
    Given the booklist server is running
    When a user requests the site
    Then that user is sent a greeting message