Feature: Customer Statement Processor

  Scenario: Process customer statement records
    Given Service operates successfully
    When Service receives deliveries of customer statement records
    Then A new report created

