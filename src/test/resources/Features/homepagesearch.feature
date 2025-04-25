@smoke
Feature: Verify Homepage Search by City, Community, Plan, or QMI name
  I want to verify that the Search by City, Community, Plan, or QMI name is working fine and redirected user to the their respected details pages.

  Scenario Outline: Verify search suggestions and redirection
    Given user is on the Home page
    When user clicks on the Search field
    And inputs the "<Category>" name "<SearchValue>"
    Then similar "<Category>" names suggestions should be visible in the dropdown
    And clicking on any "<Category>" suggestion should redirect user to "<Category>" detail page

    Examples:
      | Category  | SearchValue |
      | QMI       | place       |
      | QMI       | plan        |
      | Plan      | cove        |
      | Plan      | Plan        |
      | City      | beach       |
      | City      | dallas      |
      | Community | oaks        |
      | Community | springs     |
  
  
 