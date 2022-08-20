Feature: Compress Files

  Scenario: Upload single file
    Given the following files:
      | file1.txt |
    When I request to upload the files
    Then the response is 200
    And the extension of downloaded file is .zip
    And downloaded file has following files:
      | file1.txt |