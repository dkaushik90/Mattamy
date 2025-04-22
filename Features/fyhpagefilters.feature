#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@smoke
Feature: Verify Filters on the FYH page

Background:
  Given user is on the Home page
  And User click on the FindYourHome Menu in Header
  When select any random option
  Then User should be redirected to the FYH Page for the Selected option

  @ignore
  Scenario: Verify the selected option matches the dropdown selection on the FYH page
    And Randomly selected option name should match the option name preselected in the dropdown on the FYH page
    
 @ignore
  Scenario Outline: Verify the Count of the communities/plans and QMIs
    When User click on the "<TabName>" tab
    And "<TabName>" count is visible
    Then Count of the "<TabName>" should be same as the cards showing below in listing
    
    Examples:
      | TabName         |
      | Communities     |
      | Quick Move-Ins  |
      | Plans           |
  
@smoke
Scenario Outline: Verify that selecting "<SortType>" filter updates listings on the "<TabName>" tab
  When User clicks on "<TabName>" tab and clicks on the "<SortType>" dropdown
  Then for the selected "<TabName>" the "<SortType>" list should be visible to user
  And choosing any value from the "<SortType>"dropdown on the selected "<TabName>" tab
  Then Listing should be updated as per the selected "<SortType>" for the "<TabName>" tab

Examples:
  | TabName         | SortType  |
  | Communities     | Price     |
  | Communities     | A-Z       |
  | Quick Move-Ins  | Price     |
  | Quick Move-Ins  | Size      |
  | Quick Move-Ins  | A-Z       |
  | Plans           | Price     |
  | Plans           | Size      |
  | Plans           | A-Z       |
  
  


  
  