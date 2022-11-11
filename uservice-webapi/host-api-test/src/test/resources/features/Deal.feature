Feature: Deal Service Tests

  Scenario: Should get the version of the deal service
    When I make a GET call on /deal/version
    Then I should receive 200 response status code
    And should receive a non-empty body

