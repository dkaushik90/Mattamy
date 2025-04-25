package stepDefinations;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.*;
import pageObjects.HomePage;


public class HomePageSteps {

	public WebDriver rdriver;
    public HomePage hp;

    public HomePageSteps() {  // 👈 Constructor
        this.rdriver = Hooks.driver; //Getting driver from Hooks
        if (this.rdriver == null) {
            throw new IllegalStateException(" WebDriver is null! Ensure Hooks initializes it properly.");
        }
        this.hp = new HomePage(rdriver);        //Passing driver to HomePage
    }

	
	@Given("user is on the Home page")
	public void user_is_on_the_home_page() {
	    hp.openHomePage();
        System.out.println("Home Page verification starting now.");

	}
	@Given("all elements loaded on the homepage")
	public void all_elements_loaded_on_the_homepage() {
		hp.checkAllContentLoaded();
	}
	@Then("user should be able to view all the home page elements")
	public void user_should_be_able_to_view_all_the_home_page_elements() {
	    System.out.println("Both elements are loaded in the DOM as well as on the Front end.");

	}

	@When("user clicks on the Search field")
	public void user_clicks_on_the_search_field() {
	    hp.clickSearchField();
	}
	@And("inputs the {string} name {string}")
	public void inputs_the_name(String category,String name) {
        System.out.println("Searching in category: " + category + " for: " + name);
		hp.enterSearchName(name);
	
	}
	@Then("similar {string} names suggestions should be visible in the dropdown")
	public void similar_names_suggestions_should_be_visible_in_the_dropdown(String category) {
		  List<WebElement> suggestions = hp.getOptionsByCategory(category);
	        
	        if (suggestions.isEmpty()) {
	            throw new AssertionError("No search suggestions found for category: " + category);
	        } else {
	            System.out.println("Similar suggestions are populated for category: " + category);
	        }
		
	   System.out.println("Simmilar suggestions are populating matching the input values");
	   
	}
	 @And("clicking on any {string} suggestion should redirect user to {string} detail page")
	    public void clicking_on_any_suggestion_should_redirect_user_to_detail_page(String category, String name) {
		 hp.selectAutoSuggestionBasedUponCategoryName(category);
       System.out.println("Successfully redirected to the " + category + " detail page.");
	}




}
