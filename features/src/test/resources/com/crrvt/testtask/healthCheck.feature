Feature: Compress Files

  Scenario: check /health endpoint
    When I check health status
    Then the response status is 200
    And the response value at path '/status' equals 'UP'