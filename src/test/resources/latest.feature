Feature: Latest Exchange Rates

  Scenario: Calling latest API without parameters
    Given Valid api key is provided
    When User call latest API without parameters
    Then Call is successful and default values are returned

  Scenario Outline: Calling latest API with single parameter
    Given Valid api key is provided
    When User call latest API with single parameter "<parameter>" = "<value>"
    Then Status code <status_code> is returned
    And Returned data for parameter "<parameter>" = "<value>" is correct
    Examples:
      | parameter | value   | status_code |
      | base      | USD     | 200         |
      | base      | XOF     | 200         |
      | symbols   | USD     | 200         |
      | symbols   | USD,OMR | 200         |

  Scenario Outline: Calling latest API with multiple parameters
    Given Valid api key is provided
    When User call latest API with both parameters base = "<base>" symbols = "<symbols>"
    Then Status code <status_code> is returned
    And Returned data for parameters base = "<base>" symbols = "<symbols>" is correct
    Examples:
      | base | symbols       | status_code |
      | USD  | USD,PLN,XOF   | 200         |
      | XOF  | XOF           | 200         |
      | PLN  | USD           | 200         |
      | pln  | USD,A,XYZ,EUR | 200         |




