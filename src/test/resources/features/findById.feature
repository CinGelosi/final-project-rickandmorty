@regression
Feature: Character Detail Display

  Description:

  As a user
  I want to get details about a character, including name,
  status, species, gender, origin, location, image and episodes in which it appears.
  so that I can get more information

  Background:
    Given a user logs into the web

  @smoke
  Scenario Outline: Validate the user can find characters by id
    When the user search by id <id>
    Then the status code is 200
    And the character with id <id> is present
    And the character response structure is as expected
    Examples:
      | id  |
      | 72  |
      | 140 |

  Scenario: Validate the status code with an invalid id
    When the user search by a wrong id 999999
    Then the status code is 404
    And the error message equals to "Character not found"

  Scenario: Validate the status code when user types text instead of numbers in id field
    When the user search by text "hola" on the id field
    Then the status code is 500
    And the error message equals to "Hey! you must provide an id"

