package stepDefinations;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	
	public static WebDriver driver;

    @Before(order = 0)  // with hooks always use void type methods and do not use static.
    public void initializeDriver() {
        System.out.println("Starting WebDriver...");
    	if (driver == null) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    	}  
    	else {
            System.out.println("WebDriver was already initialized.");
        }
    }
       @Before(order = 1)
    public void startScenario(Scenario scenario) {
        System.out.println("Starting Scenario: " + scenario.getName());
    }

    @After(order = 1)
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Test Failed! Capturing screenshot...");
            captureScreenshot("Failure_" + scenario.getName());
        }
    }

    @After(order = 0)
    public void teardown() {
        if (driver != null) {
            System.out.println("Closing WebDriver...");
            driver.quit();
            driver = null; // Prevent stale references
        }
        else {
            System.out.println("WebDriver was already null before teardown.");
        }
    }

    @BeforeStep
    public void beforeEachStep() {
        System.out.println(" Executing next step...");
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (scenario.isFailed()) {
            captureScreenshot("Step_Failure_" + scenario.getName());
        }
    }
    

    private void captureScreenshot(String fileName)  {
        try {
        	if (driver == null) {
                System.out.println("WebDriver is null. Cannot capture screenshot.");
                return;
            }
        	 fileName = fileName.replaceAll("[\\\\/:*?\"<>|]", "_");
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("target/screenshots/" +fileName + "_" + timestamp + ".png");
            FileUtils.copyFile(screenshot, destinationFile);
            System.out.println("Screenshot saved at: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
    }
    
    public static WebDriver getDriver()
    {
    	return driver;
    }

    }

