Feature: Customer Statement Processor

  Scenario: Process single customer statement records
    Given Record doesn't exist
    When I call scp to process a record
    Then A new record created

