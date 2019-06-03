Feature: Add book to database
    As a booklist user, I want to add a book to the database so I can reference its information later
    
Scenario: Update the database with a new book
    Given the booklist server is running and there is a new book I want to save
    When I send a POST request to the booklist server with the book's information
    Then the database adds a book entry to the database with that information