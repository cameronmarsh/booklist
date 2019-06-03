Feature: Update a book in the database
    
Scenario: Mark a book as read
    Given a book in the database is unread
    When I read the book and send a post request to mark the book as read
    Then the read field in the database is updated to true