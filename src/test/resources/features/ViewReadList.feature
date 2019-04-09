Feature: View Read List
    As a user, I want to see the books I've read

    Scenario: Viewing read list
        Given a list of books I've read
        When I request the read list
        Then information about those books is returned