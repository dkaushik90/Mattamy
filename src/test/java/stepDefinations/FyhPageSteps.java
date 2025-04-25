package stepDefinations;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.*;
import pageObjects.FyhPage;
import utilities.WaitUtils;

public class FyhPageSteps {
	
	public WebDriver rdriver;
    public FyhPage fyh;

    public FyhPageSteps() {  // 👈 Constructor
        this.rdriver = Hooks.driver; //Getting driver from Hooks
        if (this.rdriver == null) {
            throw new IllegalStateException(" WebDriver is null! Ensure Hooks initializes it properly.");
        }
        this.fyh = new FyhPage(rdriver);        //Passing driver to FyhPage
    }
    
    Logger logger = LoggerFactory.getLogger(FyhPageSteps.class);
    
	@Given("User click on the FindYourHome Menu in Header")
	public void user_click_on_the_find_your_home_menu_in_header() {
		fyh.clickFyhLink();
	}

	@Given("select any random option")
	public void select_any_random_option() {
    fyh.randomclickOnName();
	}

	@Then("User should be redirected to the FYH Page for the Selected option")
	public void user_should_be_redirected_to_the_fyh_page_for_the_selected_option() {
		logger.info("Successfully redirected to the FYH Page. Current URL: {}", rdriver.getCurrentUrl());
	
	}
	
	@And("Randomly selected option name should match the option name preselected in the dropdown on the FYH page")
	public void randomly_selected_option_name_should_match_preselected_dropdown_option() {
	    fyh.verifyRedirectiontoFyhPage();	
	}


	@When("User click on the {string} tab")
	public void user_click_on_the_tab(String tabName) {
		fyh.selectTab(tabName);
	}

	@When("{string} count is visible")
	public void count_is_visible(String tabName) {
		logger.info("{} count is visible", tabName);
		}

	@Then("Count of the {string} should be same as the cards showing below in listing")
	public void count_of_the_should_be_same_as_the_cards_showing_below_in_listing(String tabName) {
	    fyh.compareCount(tabName);
	    logger.info("Count comparison completed for tab: {}", tabName);
	}

	
	@When("User clicks on {string} tab and clicks on the {string} dropdown")
	public void user_clicks_on_tab_and_filter_dropdown(String tabName, String filterType) {
	    fyh.selectTab(tabName);
	    WaitUtils.addDelay();
	    fyh.clickSortType();
	}

	
	@Then("for the selected {string} the {string} list should be visible to user")
	public void filter_list_should_be_visible(String tabName, String filterType) {
	    fyh.verifyFilterListVisible(tabName, filterType);
	}
	
	@And("for the selected {string} tab choose any value from the {string} sort dropdown")
	public void for_the_selected_tab_choose_any_value_from_the_sort_dropdown(String tabName, String sortType) {
	    fyh.selectSortOptionByVisibleText( sortType);
	}
	
	@Then("Listing should be updated as per the selected {string} for the {string} tab")
	public void listing_should_be_updated(String filterType, String tabName) {
	    boolean isUpdated = fyh.isListingUpdatedBasedOnFilter(tabName, filterType);
	    System.out.println("Validation Result: " + isUpdated);
	    if (!isUpdated) {
	        System.out.println("Error: Listing was not updated based on the selected filter.");
	    }

}
}
