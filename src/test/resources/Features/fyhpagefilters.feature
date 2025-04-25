@smoke
Feature: Verify Filters on the FYH page

Background:
  Given user is on the Home page
  And User click on the FindYourHome Menu in Header
  When select any random option
  Then User should be redirected to the FYH Page for the Selected option
  
@smoke
Scenario Outline: Verify that selecting "<SortType>" filter updates listings on the "<TabName>" tab
  When User clicks on "<TabName>" tab and clicks on the "<SortType>" dropdown
  Then for the selected "<TabName>" the "<SortType>" list should be visible to user
  And for the selected "<TabName>" tab choose any value from the "<SortType>" sort dropdown
  Then Listing should be updated as per the selected "<SortType>" for the "<TabName>" tab

Examples:
  | TabName         | SortType  |
  | Communities     | $ - $$$   |
  | Communities     | A - Z     |
  | Quick Move-Ins  | $ - $$$   |
  | Quick Move-Ins  | Sq. Ft.   |
  | Quick Move-Ins  | A - Z     |
  | Plans           | $ - $$$   |
  | Plans           | Sq. Ft.   |
  | Plans           | A - Z     |
  
  


  
  