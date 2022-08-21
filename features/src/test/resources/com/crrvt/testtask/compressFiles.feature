Feature: Compress Files

  Scenario: Upload single file
    Given files are:
      | file1.txt |
    And the Content-Type header is 'multipart/form-data;'
    When I request to compress the files
    Then the response status is 200
    And the extension of downloaded file is '.zip'
    And downloaded archive contains all files from request

  Scenario: Upload several files
    Given files are:
      | file1.txt |
      | file2.txt |
      | file3.txt |
    And the Content-Type header is 'multipart/form-data;'
    When I request to compress the files
    Then the response status is 200
    And the extension of downloaded file is '.zip'
    And downloaded archive contains all files from request