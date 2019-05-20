Feature: View all Titles
    As a booklist user, I want to view all book titles saved
    
Scenario: Navigate to titles page
    Given the booklist server is alive and running
    When a user requests the endpoint to see all titles
    Then a list of all books saved is returned

