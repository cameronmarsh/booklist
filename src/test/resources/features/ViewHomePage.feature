# new feature
# Tags: optional
    
Feature: View home page
    As a user, I want to navigate to the book list application site
    
Scenario: Navigating to site
    Given the booklist server is running
    When a user requests the site
    Then that user is redirected to their read list page