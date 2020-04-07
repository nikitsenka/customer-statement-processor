Feature: Customer Statement Processor

  Scenario: Process customer statement records json
    Given Service operates successfully
    When Service receives json of customer statement records
    Then A new report created

  Scenario: Process customer statement records xml
    Given Service operates successfully
    When Service receives xml of customer statement records
    Then A new report created
