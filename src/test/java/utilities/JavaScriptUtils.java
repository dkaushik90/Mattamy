package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {
	
    private WebDriver rdriver;
    
    // Constructor
    public JavaScriptUtils(WebDriver driver) {
        this.rdriver = driver;
    }
    
 // Scroll to an element even with lazy loading
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) rdriver;

        // Keep scrolling until element is in view
        while (!isElementInView(element)) {
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            try {
                Thread.sleep(1000); // Give time for lazy load
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Scrolled to element: " + element.toString());
    }

    // Check if element is in viewport
    private boolean isElementInView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) rdriver;
        return (boolean) js.executeScript(
            "var rect = arguments[0].getBoundingClientRect();" +
            "return (rect.top >= 0 && rect.bottom <= window.innerHeight);", element);
    }

    // Scroll slightly up after reaching element (to avoid getting stuck at bottom)
    public void scrollUpSlightly() {
        JavascriptExecutor js = (JavascriptExecutor) rdriver;
        js.executeScript("window.scrollBy(0, -200);");
    }

    
    //wait for complete page load
    public void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) rdriver;
        
        while (true) {
            String readyState = (String) js.executeScript("return document.readyState;");
            if ("complete".equals(readyState)) {
                System.out.println("Page fully loaded.");
                break;
            }
        }
    }

  //  Scroll to an element in a smooth manner
    
   public void scrollToElementWithoutLazyLoading(WebElement element) {
	   JavascriptExecutor js = (JavascriptExecutor) rdriver;

       js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
   }
    
   public void scrollToBottom() {
	   JavascriptExecutor js = (JavascriptExecutor) rdriver;

	   long lastHeight = (long) js.executeScript("return document.body.scrollHeight;");
	    
	    while (true) {
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        try {
	            Thread.sleep(1000);  // Allow time for loading (adjust as needed)
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        long newHeight = (long) js.executeScript("return document.body.scrollHeight;");
	        if (newHeight == lastHeight) {
	            break;  // Exit loop if height doesn't change (i.e., no more content)
	        }
	        lastHeight = newHeight;
	    }
	}

   public void waitForLazyLoadingToFinish() {
	    JavascriptExecutor js = (JavascriptExecutor) rdriver;
	   // WebDriverWait wait = new WebDriverWait(rdriver, Duration.ofSeconds(30)); // Adjust timeout as needed

	    long lastHeight = (long) js.executeScript("return document.body.scrollHeight;");
	    
	    while (true) {
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	        try {
	            Thread.sleep(2000); // Allow time for lazy-loaded content to appear
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        long newHeight = (long) js.executeScript("return document.body.scrollHeight;");
	        
	        // If height hasn't changed after scrolling, assume all content has loaded
	        if (newHeight == lastHeight) {
	            System.out.println("Lazy loading complete. Page height stabilized at: " + newHeight);
	            break;
	        }

	        lastHeight = newHeight;
	    }
	}
   
   //Scrolling with pixels for lazy loading pages.
   public void scrollByPixelsLazyLoad(int pixelStep, int stopAboveBottom) {
	    JavascriptExecutor js = (JavascriptExecutor) rdriver;

	    long lastHeight = (long) js.executeScript("return document.body.scrollHeight;");
	    long currentPosition;

	    while (true) {
	        currentPosition = (long) js.executeScript("return window.scrollY;");
	        long newHeight = (long) js.executeScript("return document.body.scrollHeight;");

	        // Target stopping position
	        long targetPosition = Math.min(currentPosition + pixelStep, newHeight - stopAboveBottom);

	        // Scroll smoothly to the target position
	        js.executeScript("window.scrollTo({top: arguments[0], behavior: 'smooth'});", targetPosition);

	        // Allow time for content to load (adjustable)
	        try {
	            Thread.sleep(1500); // Increase delay for lazy-loading elements
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // Recalculate height after waiting
	        newHeight = (long) js.executeScript("return document.body.scrollHeight;");

	        // Stop if we've reached the stopping point
	        if (currentPosition >= newHeight - stopAboveBottom - pixelStep) {
	            System.out.println("Stopped  " + stopAboveBottom + "px above the bottom.");
	            break;
	        }

	        // Stop if no new content loads (lazy loading has finished)
	        if (lastHeight == newHeight) {
	            System.out.println("No new content loaded, stopping.");
	            break;
	        }

	        lastHeight = newHeight;  // Update last height for next iteration
	    }
	}
   //Optimized Scroll to "Just Above Bottom"
   public void scrollNearBottom(int offset) {
	    JavascriptExecutor js = (JavascriptExecutor) rdriver;
	    long pageHeight = (long) js.executeScript("return document.body.scrollHeight;");
	    long targetScrollPosition = pageHeight - offset;

	    // Ensure target position is within a valid range
	    if (targetScrollPosition > 0) {
	        js.executeScript("window.scrollTo({top: arguments[0], behavior: 'smooth'});", targetScrollPosition);
	    }
	}
    // Scroll to the top of the page
   public void scrollToTop() {
	   JavascriptExecutor js = (JavascriptExecutor) rdriver;

       js.executeScript("window.scrollTo(0, 0);");
   }
}

