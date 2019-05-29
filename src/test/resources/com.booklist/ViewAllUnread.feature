Feature: View unread books
    As a booklist user, I want to be able to see the books marked unread so I can choose which book to read next
    
Scenario: Get list of unread books
    Given the booklist server has books marked unread
    When a user requests the endpoint for unread books
    Then a list of books marked unread is returned