package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.CommonUtilities;
import utilities.JavaScriptUtils;
import utilities.WaitUtils;

public class HomePage {

	public WebDriver rdriver;
	JavaScriptUtils jsUtils;
	WebDriverWait wait;

	public HomePage(WebDriver ldriver) { // Constructor receives driver
		this.rdriver = ldriver;
		if (this.rdriver == null) {
			throw new IllegalStateException("WebDriver is null! Ensure Hooks initializes it properly.");
		}
		PageFactory.initElements(rdriver, this);
		jsUtils = new JavaScriptUtils(rdriver);
		wait = new WebDriverWait(rdriver, Duration.ofSeconds(30));
	}

	@FindBy(xpath = "//div[@class='mt-1 flex justify-center w-auto']")
	@CacheLookup
	WebElement ctaGetToKnowUs;

	@FindBy(xpath = "//input[@id='mastheadSeachBox']")
	@CacheLookup
	WebElement searchField;

	@FindBy(xpath = "//span[normalize-space()='Explore our locations near you']")
	WebElement txtExploreLocations;

	@FindBy(xpath = "//img[contains(@alt,'Large white kitchen with vertical white cabinets, a large white island and round, grey bar stools.')]")
	WebElement imgDesignedInMind;

	@FindBy(xpath = "//p[contains(text(),'Quick Move-Ins')]/ancestor::div[contains(@class, 'flex')]/following-sibling::div//a")
	List<WebElement> allQMIOptions;

	@FindBy(xpath = "//p[contains(text(),'Market')]/ancestor::div[contains(@class, 'flex')]/following-sibling::div//a")
	List<WebElement> allCityOptions;

	@FindBy(xpath = "//p[contains(text(),'Plans')]/ancestor::div[contains(@class, 'flex')]/following-sibling::div//a")
	List<WebElement> allPlanOptions;

	@FindBy(xpath = "//p[contains(text(),'Communities')]/ancestor::div[contains(@class, 'flex')]/following-sibling::div//a")
	List<WebElement> allCommunitiesOptions;

	@FindBy(xpath = "//div[@class='flex flex-row w-full']")
	WebElement detailPageQmiPlanName;

	@FindBy(tagName = "h1")
	WebElement h1tag;

	@FindBy(xpath = "//div[@class='sc-Axmtr csROLq relative h-full text-xs font-graphie w-auto select-none']")
	WebElement MarketPage;

	public void openHomePage() {
		rdriver.get("https://mattamyhomes.com/?country=USA");
		rdriver.navigate().refresh();
		System.out.println(rdriver.getTitle());
		System.out.println(rdriver.getCurrentUrl());
	}

	public void checkAllContentLoaded() {
		try {
			// jsUtils.waitForPageLoad();

			jsUtils.scrollByPixelsLazyLoad(200, 200);
			jsUtils.scrollToElement(imgDesignedInMind);

			wait.until(ExpectedConditions.visibilityOf(imgDesignedInMind));

			if (imgDesignedInMind.isDisplayed()) {
				System.out.println("Designed in Mind Section Image is visible!");
			} else {
				System.out.println("Designed in Mind Section Image not visible");
			}

			jsUtils.scrollToElement(txtExploreLocations);
			wait.until(ExpectedConditions.visibilityOf(txtExploreLocations));

			if (txtExploreLocations.isDisplayed()) {
				System.out.println("Text is visible!");
			} else {
				System.out.println("Text not found.");
			}

			wait.until(ExpectedConditions.elementToBeClickable(ctaGetToKnowUs)).click();
			String expectedURL = "https://mattamyhomes.com/about/about-mattamy";
			wait.until(ExpectedConditions.urlToBe(expectedURL));
			String currentUrl = rdriver.getCurrentUrl();
			if (currentUrl.equals(expectedURL)) {
				System.out.println("Successfully navigated to the next page: " + currentUrl);
			} else {
				System.out.println("Failed to navigate. Current URL: " + currentUrl);
			}

			System.out.println("All required elements are loaded on the homepage.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());

		}
	}

	public void clickSearchField() {
		searchField.click();
	}

	public void enterSearchName(String searchName) {
		clickSearchField();
		searchField.clear();
		// wait.until(ExpectedConditions.attributeToBeNotEmpty(searchField, "value"));
		// Actions actions = new Actions(rdriver);
		// actions.sendKeys(searchField, qmiName).perform();

		for (char c : searchName.toCharArray()) {
			searchField.sendKeys(String.valueOf(c));
			WaitUtils.addDelay();
		}
		searchField.click(); // Click to ensure focus
		WaitUtils.addDelay();

		// searchField.sendKeys(qmiName); // Inserts the QMI Name into the field
		searchField.sendKeys(Keys.BACK_SPACE);
		WaitUtils.addDelay();

		searchField.sendKeys(searchName.substring(searchName.length() - 1));
		WaitUtils.addDelay();

		searchField.click();

	}

	public List<WebElement> getOptionsByCategory(String category) {
		if (category.equals("QMI")) {
			return allQMIOptions;
		} else if (category.equals("Plan")) {
			return allPlanOptions;
		} else if (category.equals("City")) {
			return allCityOptions;
		} else if (category.equals("Community")) {
			return allCommunitiesOptions;
		} else {
			throw new IllegalArgumentException("Invalid category: " + category);
		}

	}

	public void selectAutoSuggestionBasedUponCategoryName(String category) {
		List<WebElement> suggestions = getOptionsByCategory(category);
		wait.until(ExpectedConditions.visibilityOfAllElements(suggestions));
		if (suggestions.isEmpty()) {
			throw new AssertionError("No search suggestions found for category: " + category);
		}
		System.out.println("Total Suggestion available are -------" + suggestions.size());
		// Print all suggestions properly
		System.out.println("All Suggestions are :");
		for (WebElement suggestion : suggestions) {
			System.out.println("---------- " + suggestion.getText().trim());
		}

		WebElement selectedSuggestion = CommonUtilities.getRandomElement(suggestions);
		String suggestionText = selectedSuggestion.getText().trim();
		System.out.println("Suggestion Name Selected from the List-------" + suggestionText);
		selectedSuggestion.click();
		WaitUtils.addDelay();
		if (category.equals("City")) {
			wait.until(ExpectedConditions.visibilityOf(MarketPage));
			String marketName = MarketPage.getText().trim();
			System.out.println("Redirected to Market page ----" + " " + marketName);
			if (suggestionText.contains(marketName)) {
				System.out.println("Successfully redirected to the FYH Page.-----" + suggestionText + marketName);
			} else {
				System.out.println("Failed to redirect on the FYH page");
			}
		} else {
			String h1 = h1tag.getText();
			System.out.println("h1 heading for the detail page is " + h1);
			CommonUtilities.verifyPageLoad(rdriver);
			System.out.println("Redirected suggestion Page Title is" + rdriver.getTitle());

			if (suggestionText.contains(h1)) {
				System.out.println("Successfully redirected to the category detail page." + "suggestion text is---- "
						+ suggestionText + "hi is---- " + h1);
			} else {
				System.out.println("Failed to redirect on the detail page");

			}
		}

	}
}
