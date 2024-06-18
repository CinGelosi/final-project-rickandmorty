@regression
Feature: Search Rick and Morty characters

  Description:
  As a user,
  I want to be able to search for Rick and Morty characters by name,
  so that I can get detailed information about them, including their name,
  status, location, their image, and the episodes they appear in

  Background:
    Given a user logs into the web

  @smoke
  Scenario Outline: Validate the user can find characters by name
    When the user search by name "<name>"
    Then the status code is 200
    And the character with name "<name>" is present

    Examples:
      | name    |
      | rick    |
      | sanchez |
      | ri      |

  Scenario: Validate the status code with an invalid name
    When the user search by a wrong name "Paulina"
    Then the status code is 404
    And the error message equals to "There is nothing here"

