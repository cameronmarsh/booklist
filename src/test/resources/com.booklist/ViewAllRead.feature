Feature: View all read books
    As a booklist user, I want to view only the books I've read
    
Scenario: Get list of read books
    Given the booklist server has books marked as read
    When a user requests the endpoint to view read books
    Then a list of books marked as read is returned