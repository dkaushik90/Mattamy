package utilities;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CommonUtilities {
	public static void verifyPageLoad(WebDriver driver) {
        String url = driver.getCurrentUrl();
        Response response = RestAssured.get(url);
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Page did not load successfully! Status Code: " + response.getStatusCode());
        }
        System.out.println("Page loaded successfully: " + url);
    }

	
	public static WebElement getRandomElement(List<WebElement> elements)
	{
		 if (elements == null || elements.isEmpty()) {
	            throw new IllegalArgumentException("No elements found to select from!");
	        }
		 Random random = new Random();
		 int randomIndex = random.nextInt(elements.size());
		 return elements.get(randomIndex);
	}
}
