
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class LemdaTestAllAssignment {
    public static final String USERNAME = "bikashroshan000";
    public static final String ACCESS_KEY = "c8DgoZllcXSR45PKWWN3osywJFWvrkQv9BDHYACs06ckqC6Gah";
    public static final String GRID_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("128");
        browserOptions.addArguments("--disable-popup-blocking");
        browserOptions.addArguments("--disable-infobars");
        browserOptions.addArguments("--disable-notifications");
        browserOptions.addArguments("disable-features=CookiesWithoutSameSiteMustBeSecure");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "bikashroshan000");
        ltOptions.put("accessKey", "c8DgoZllcXSR45PKWWN3osywJFWvrkQv9BDHYACs06ckqC6Gah");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        ltOptions.put("video", true);
        ltOptions.put("visual", true);
        ltOptions.put("console", true);
        ltOptions.put("project", "LamdaTest");
        ltOptions.put("name", "Assignment");
        browserOptions.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL(GRID_URL), browserOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

//    public void setUpViaChrome() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//    }

    @Test(priority = 1)
    public void testScenarioOne() {
        // Step 1: Open LambdaTest Url
        driver.get("https://www.lambdatest.com/selenium-playground");

        // Step 2: Click “Simple Form Demo” under Input Forms
        WebElement simpleFormDemo = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Simple Form Demo")));
        simpleFormDemo.click();

        // Step 3: Validate the URL contains "simple-form-demo"
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("simple-form-demo"), "URL isn't Valid");

        // Step 4: Create a variable
        String message = "Welcome to LambdaTest";

        // Step 5: Enter the message in the “Enter Message” text box
        WebElement enterMessageTextField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-message")));
        enterMessageTextField.sendKeys(message);

        // Step 6: Click “Get Checked Value” button
        WebElement getCheckedValueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("showInput")));
        getCheckedValueButton.click();

        // Step 7: Validate message is displayed at the “Your Message” section
        WebElement displayMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

        String displayedMessage = displayMessage.getText();
        Assert.assertEquals(displayedMessage, message, "Message isn't displayed correctly");
    }

    @Test(priority = 2)
    public void testDragDropSliderAssignment2() {
        // Step 1: Open the URL of the assignment
        driver.get("https://www.lambdatest.com/selenium-playground");

        // Step 2: Click “Drag & Drop Sliders”
        WebElement dragAndDropSlider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Drag & Drop Sliders")));
        dragAndDropSlider.click();

        // Step 3: Select the slider "Default value 15" and drag it to 95
        WebElement slider = driver.findElement(By.xpath("//input[@value='15']"));
        Actions action = new Actions(driver);
        action.dragAndDropBy(slider, 215, 0).perform();  // xOffset to move to 95

        // Step 4: Validate whether the range value shows 95
        WebElement sliderValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rangeSuccess")));
        String rangeValue = sliderValue.getText();
        Assert.assertEquals(rangeValue, "95", "Slider Value is not 95");
    }

    @Test(priority = 3)
    public void testInputFormSubmit() {
        // Step 1: Open the URL
        driver.get("https://www.lambdatest.com/selenium-playground");

        // Step 2: Click “Input Form Submit” under “Input Forms”
        WebElement inputFormSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Input Form Submit")));
        inputFormSubmit.click();

        // Step 3: Click "Submit" without filling the form anything
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='submit'])[2]")));
        submitButton.click();
        // As unable to find the error in the DOM, So I noticed error message is getting trigger from browser. That's why i am using javaScript Executor to verify the browser based error message
        // Find the 'Company' field (the field that triggers the error)
        WebElement companyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("company")));

        // Get the validation message using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", companyField);
        // Print the validation message (for debug purpose)
        System.out.println("Validation Message: " + validationMessage);

        // Assert the validation message is as expected
        Assert.assertEquals(validationMessage, "Please fill out this field.");


        // Step 5: Fill in Name, Email, and other fields

        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
        nameField.sendKeys("Bikash Kumar Roshan");
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("inputEmail4")));
        emailField.sendKeys("bikashroshan000@gmail.com");
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("inputPassword4")));
        passwordField.sendKeys("bkr123");
        companyField.sendKeys("StockEdge");
        WebElement websiteField = wait.until(ExpectedConditions.elementToBeClickable(By.id("websitename")));
        websiteField.sendKeys("https://bikash.com");

        // Step 6: Select "United States" from the Country drop-down
        Select countryDropdown = new Select(driver.findElement(By.name("country")));
        countryDropdown.selectByVisibleText("India");

        WebElement cityField = wait.until(ExpectedConditions.elementToBeClickable(By.id("inputCity")));
        cityField.sendKeys("Giridih");
        WebElement addressField = wait.until(ExpectedConditions.elementToBeClickable(By.id("inputAddress1")));
        addressField.sendKeys("Birsha Chock");
        WebElement addressField2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("inputAddress2")));
        addressField2.sendKeys("Jharkhand");
        WebElement stateField = wait.until(ExpectedConditions.elementToBeClickable(By.id("inputState")));
        stateField.sendKeys("jharkhand");
        WebElement zipField = wait.until(ExpectedConditions.elementToBeClickable(By.id("inputZip")));
        zipField.sendKeys("815301");

        // Step 7: Submit the form
        submitButton.click();

        // Step 8: Validate success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Thanks for contacting us')]")));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message not displayed");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
