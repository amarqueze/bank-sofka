Feature: Find Client API

  Scenario: Found client by DNI
    Given a client exists with DNI "123456789"
    When the client information is requested for DNI "123456789"
    Then the client information should be returned

  Scenario: Client not found
    Given no client exists with DNI "987654321"
    When the client information is requested for DNI "987654321"
    Then a client not found error should be returned
