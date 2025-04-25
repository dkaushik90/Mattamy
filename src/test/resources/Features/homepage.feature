@Regression @smoke
Feature: Home Page Verification
  @smoke
  Scenario: Verify the Home Page is loaded successfully with all elements and contents
    Given user is on the Home page
    And all elements loaded on the homepage
   Then user should be able to view all the home page elements

 
