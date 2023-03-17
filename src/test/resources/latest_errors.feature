Feature: Latest Exchange Rates Error Handling

  #Authorization Tests
  Scenario: Calling latest API with invalid token
    Given Invalid api key is provided
    When User call latest API with single parameter "base" = "EUR"
    Then Status code 401 is returned

  Scenario: Calling latest API with empty token
    Given Empty api key is provided
    When User call latest API without parameters
    Then Status code 401 is returned

  #Incorrect parameters
  Scenario Outline: Calling latest API with invalid symbol base
    Given Valid api key is provided
    When User call latest API with single parameter "base" = "<symbol>"
    Then Status code <status_code> is returned
    Examples:
      | symbol  | status_code |
      | AAA     | 400         |
      | A       | 400         |
      | bbbbb   | 400         |
      | xyz     | 400         |
      | USD,EUR | 400         |

  Scenario Outline: Calling latest API with invalid rates
    Given Valid api key is provided
    When User call latest API with single parameter "symbols" = "<rates>"
    Then Status code <status_code> is returned
    Examples:
      | rates     | status_code |
      | AAA       | 400         |
      | A         | 400         |
      | bbbbb     | 400         |
      | xyz       | 400         |
      | USD.ER    | 400         |
      | USD!!!!ER | 400         |

  Scenario: Calling not existing API
    Given Incorrect api url is provided
    When User call incorrect API
    Then Status code 404 is returned



