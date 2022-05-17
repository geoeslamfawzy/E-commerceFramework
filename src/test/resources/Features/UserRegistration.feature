Feature: User Registration
  I want to check whether the user can register successfully

  Scenario: User Registration
    Given : User in the home page
    When : I Click on the register link
    And I entered the user data
    Then : The registration complete successfully and moves the user to the home page