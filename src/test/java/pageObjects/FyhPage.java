package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

public class FyhPage {

	public WebDriver rdriver;
	JavaScriptUtils jsUtils;
	WebDriverWait wait;
	String selectedLink;

	public FyhPage(WebDriver ldriver) { // Constructor receives driver
		this.rdriver = ldriver;
		if (this.rdriver == null) {
			throw new IllegalStateException("WebDriver is null! Ensure Hooks initializes it properly.");
		}
		PageFactory.initElements(rdriver, this);
		jsUtils = new JavaScriptUtils(rdriver);
		wait = new WebDriverWait(rdriver, Duration.ofSeconds(30));	
	
}
	
	@FindBy(xpath = "//p[normalize-space()='Find Your Dream Home']")
	@CacheLookup
	WebElement fyhLink;
	
	@FindBy(xpath = "//div[@class='relative font-trade-gothic-20 text-2xl text-white leading-9 tracking-tight'] | //div[@class='sc-fzpdyU bpAxtW']")
	List<WebElement> fyhOptions;	
	
	@FindBy(xpath = "//div[@class='sc-Axmtr csROLq relative h-full text-xs font-graphie w-auto select-none']")
	WebElement nameSelectedInsideDropdown;
	
	@FindBy(xpath = "//button[contains(@aria-label, 'Plans')]")
	WebElement PlanTab;
	
	@FindBy(xpath = "//button[contains(@aria-label, 'Quick Move-Ins')]")
	WebElement QmiTab;
	
	@FindBy(xpath = "(//div[contains(@aria-label, 'All Communities')]//span[contains(@class, 'truncate')])[1]")
	WebElement communityTab;
	
	@FindBy(xpath="//div[contains(@class, 'sc-pdkfH')]//h3")
	List<WebElement>  cardsCount;
	
	@FindBy(xpath="//div[@role='status']")
	WebElement countVisibleOverPage;
	
	@FindBy(xpath="//div[@aria-label='Dropdown price filter: No price range selected.']//button[contains(@aria-label,'dropdown list')]//*[name()='svg']")
	WebElement selectPrice;
	
	@FindBy(xpath="//div[@aria-label='Select Beds & Baths. Results will filter on the page']//button[contains(@aria-label,'dropdown list')]//*[name()='svg']")
	WebElement selectBedsBaths;
	
	@FindBy(xpath="//span[normalize-space()='$ No min']")
	WebElement selectMinPrice;
	
	@FindBy(xpath="//span[normalize-space()='$ No Max']")
	WebElement selectMaxPrice;
	
	@FindBy(xpath="//span[normalize-space()='Bedrooms']")
	WebElement selectBeds;
	
	@FindBy(xpath="//span[normalize-space()='Bathrooms']")
	WebElement selectBaths;
	
	@FindBy(xpath="//div[@aria-label='Type Filter: Select a type to filter the results.']//button[contains(@aria-label,'dropdown list')]//*[name()='svg']")
	WebElement typeValue;
	
	@FindBy(xpath="//button[@aria-label=' dropdown list']")
	WebElement SortValue;
	
	@FindBy(xpath="//div[contains(@class, 'sc-pRtcU dTzzSu')]")
	List<WebElement>  minMaxPriceList;
	
	@FindBy(xpath="//div[@class='sc-qYIQh cmQliW']")
	List<WebElement>  BedsBathsLsit;
	
	@FindBy(xpath="//div[@class='sc-pbKro bjcxym bg-white rounded-lg p-2 -mx-2 -my-1']")
	List<WebElement>  typesList;
	
	@FindBy(xpath="//div[@class='px-2 flex flex-col items-center']")
	List<WebElement>  sortOptionsList;
	
	@FindBy(xpath="//span[contains(normalize-space(), 'Sq. Ft.')]")
	List<WebElement> sortBySqFt;
	
	@FindBy(xpath = "//div[@class='sc-pKLCU LdczL font-graphie text-mattamy-blue md:text-mattamy-blue-light text-sm md:text-base leading-6 tracking-widest mt-2 md:mt-3']")
	List<WebElement> sortByName;
	
	@FindBy(xpath="//span[@class='sc-pCOPB eDrnHs flex font-trade-gothic-20 text-mattamy-blue text-3xl leading-9 md:-mb-1 ml-3 md:ml-0']")
	List<WebElement> cardsPriceList;
	
	@FindBy(xpath = "//div[contains(text(),'No results in this area')]")
	WebElement noResultsMessage;
		
	public void clickFyhLink()
	{
		WaitUtils.addDelay();

		fyhLink.click();
		
	}
	public void randomclickOnName()
	{
		WaitUtils.addDelay();
		wait.until(ExpectedConditions.visibilityOfAllElements(fyhOptions));

		for(WebElement option : fyhOptions)
		{
			System.out.println(option.getText().trim());
		}
		
		System.out.println("total number of links" + fyhOptions.size() );
	WebElement optionName =	CommonUtilities.getRandomElement(fyhOptions);
	 selectedLink = optionName.getText().trim();
		System.out.println("Name of the link selected randomly is ---" + selectedLink);
	    jsUtils.scrollToElement(optionName);

	wait.until(ExpectedConditions.elementToBeClickable(optionName)).click();
    WaitUtils.addDelay();
	}
	
	
	public void verifyRedirectiontoFyhPage()
	{
		WaitUtils.addDelay();
		 wait.until(ExpectedConditions.visibilityOf(nameSelectedInsideDropdown));
		 String textInsideDropdown =nameSelectedInsideDropdown.getText().trim();
		
		 System.out.println("Name of the clicked link-----" + selectedLink);
		    System.out.println("FYH dropdown is showing text selected--------" + textInsideDropdown);

		 if(selectedLink.contains(textInsideDropdown))
		{
			System.out.println("Redirection to FYH page is successfull" );
		}
		else
		{
			System.out.println("Redirection Failed");
		}
	String fyhPageURL= rdriver.getCurrentUrl();
	String fyhPageTitle=	rdriver.getTitle();
	System.out.println("-------" + fyhPageURL +"-------"+ fyhPageTitle);

	
	}
	
	
	public void selectTab(String tabName)
	{
		 if (tabName.equalsIgnoreCase("Communities")) {
	            wait.until(ExpectedConditions.visibilityOf(communityTab));
	            System.out.println("Communities tab is selected by default. Skipping click, waited until visible.");
	            WaitUtils.addDelay();
		 }
	          
		else if(tabName.equalsIgnoreCase("Quick Move-Ins"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(QmiTab)).click();
		    System.out.println("Clicked on tab: " + tabName);
		    WaitUtils.addDelay();
		}
		else if(tabName.equalsIgnoreCase("Plans"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(PlanTab)).click();
		    System.out.println("Clicked on tab: " + tabName);
		    WaitUtils.addDelay();
		}
		
		else
		{
			System.out.println("tab name is not showing simmilar");
		}
		
	}
	
	public boolean isTabCountVisible(String tabName) {
	    try {
	        wait.until(ExpectedConditions.visibilityOf(countVisibleOverPage));
	        return countVisibleOverPage.isDisplayed();
	    } catch (Exception e) {
	        System.out.println("Count element not visible for tab: " + tabName);
	        return false;
	    }
	}
	
	public void compareCount(String tabName)
	{
		
		int actualCardsCount = cardsCount.size();
		
		String visibleCountText = countVisibleOverPage.getText();
		int expectedCount = Integer.parseInt(visibleCountText.replaceAll("\\D+", ""));

		System.out.println("Tab: " + tabName);
	    System.out.println("Expected count from visible label: " + expectedCount);
	    System.out.println("Actual cards count: " + actualCardsCount);
	    
	    if (actualCardsCount == expectedCount) {
	        System.out.println("Count matches for tab: " + tabName);
	    } else {
	        System.out.println("Count mismatch for tab: " + tabName);
	    }
		
	}
	
	public boolean isNoResultsDisplayed() {
	    try {
	        wait.until(ExpectedConditions.visibilityOf(noResultsMessage));
	        return noResultsMessage.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public void clickSortType() {
	    try {
	        WaitUtils.addDelay(); // Small delay to allow elements to settle
	        wait.until(ExpectedConditions.visibilityOf(SortValue));
	        wait.until(ExpectedConditions.elementToBeClickable(SortValue)).click();
	        System.out.println("Clicked on the sort dropdown icon.");
	    } catch (Exception e) {
	        System.out.println("Failed to click on sort dropdown: " + e.getMessage());
	        throw e; // Rethrow to fail the test if it's critical
	    }
	}

	
	public void verifyFilterListVisible(String tabName, String filterType) {
	    WaitUtils.addDelay();

	    System.out.println("Verifying visibility of filter list for Tab: " + tabName + ", Filter: " + filterType);   
        System.out.println("Filter option count visible: " + sortOptionsList.size());

	    if (sortOptionsList != null && !sortOptionsList.isEmpty()) {
	        for (WebElement option : sortOptionsList) {
	            if (option.isDisplayed()) {
	                System.out.println("Filter option visible: " + option.getText());
	            } else {
	                throw new AssertionError("Filter option is not visible for the selected tab and filter type.");
	            }
	        }
	    } else {
	        throw new AssertionError("Sort options list is empty or not found for Tab: " + tabName + ", Filter: " + filterType);
	    }
	}

	
	public void selectSortOptionByVisibleText(String filterType) {
	    for (WebElement option : sortOptionsList) {
	        if (option.getText().trim().equalsIgnoreCase(filterType)) {
	            System.out.println("Selecting sort option: " + filterType);
	            jsUtils.scrollToElement(option);
	            option.click();
	            WaitUtils.addDelay();
	            return;
	        }
	    }
	}

	
	public boolean isListingUpdatedBasedOnFilter(String tabName, String filterType) {
	    if (isNoResultsDisplayed()) {
	        System.out.println("No results displayed for Tab: " + tabName + ", Filter: " + filterType);
	        return true; // Considered as pass since app handled it gracefully
	    }

	    switch (filterType) {
        case "$ - $$$":
            return validatePriceFilter();
        case "A - Z":
            return validateAlphabeticalOrder(tabName);
        case "Sq. Ft.":
            return validateSizeFilter(tabName);
        default:
            System.out.println("Sort type not recognized: " + filterType);
            return false;
    }
}

	// Helper Method for "Price" Validation
	private boolean validatePriceFilter() {
		
		wait.until(ExpectedConditions.visibilityOfAllElements(cardsPriceList));
	    System.out.println("List Size: " + cardsPriceList.size());
	    List<Integer> prices = new ArrayList<>();
	    for (WebElement sortByPrice : cardsPriceList) {
	        String priceText = sortByPrice.getText().replaceAll("[^0-9]", ""); // Remove non-numeric characters
	        if (!priceText.isEmpty()) {
	            prices.add(Integer.parseInt(priceText));
	        }
	    }
	    System.out.println("Price Values: " + prices);

	    // Check if the price list is in ascending order
	    for (int i = 0; i < prices.size() - 1; i++) {
	        if (prices.get(i) > prices.get(i + 1)) {
	            System.out.println("Listing is NOT updated in ascending order of prices.");
	            return false;
	        }
	    }
	    System.out.println("Listing is updated correctly in ascending order of prices.");
	    return true;
	}

	// Helper Method for "A-Z" Validation
	private boolean validateAlphabeticalOrder(String tabName) {
	    System.out.println("List Size: " + sortByName.size());
	    List<String> names = new ArrayList<>();
	    for (WebElement nameElement : sortByName) {
	        names.add(nameElement.getText().trim());
	    }
	    System.out.println("Names for Tab [" + tabName + "]: " + names);

	    // Check if names are in alphabetical order
	    for (int i = 0; i < names.size() - 1; i++) {
	        if (names.get(i).compareToIgnoreCase(names.get(i + 1)) > 0) {
	            System.out.println("Listing is NOT updated in alphabetical order.");
	            return false;
	        }
	    }
	    System.out.println("Listing is updated correctly in alphabetical order.");
	    return true;
	}

	// Helper Method for "Size" Validation
	private boolean validateSizeFilter(String tabName) {
	    System.out.println("List Size: " + sortBySqFt.size());
	    List<Integer> sizes = new ArrayList<>();
	    for (WebElement sizeElement : sortBySqFt) {
	        String sizeText = sizeElement.getText().replaceAll("[^0-9]", ""); // Extract only numeric values
	        if (!sizeText.isEmpty()) {
	            sizes.add(Integer.parseInt(sizeText));
	        }
	    }
	    System.out.println("Sizes for Tab [" + tabName + "]: " + sizes);

	    // Check if sizes are in ascending order
	    for (int i = 0; i < sizes.size() - 1; i++) {
	        if (sizes.get(i) > sizes.get(i + 1)) {
	            System.out.println("Listing is NOT updated in ascending order of sizes.");
	            return false;
	        }
	    }
	    System.out.println("Listing is updated correctly in ascending order of sizes.");
	    return true;
	}

}