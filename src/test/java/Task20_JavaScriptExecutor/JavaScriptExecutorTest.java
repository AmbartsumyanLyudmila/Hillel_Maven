package Task20_JavaScriptExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class JavaScriptExecutorTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void driverSet() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Website opened ");
    }

    @Test(priority = 1, description = "Verify that page title = 'Hillel Qauto'")
    public void checkPageTitleUseExplicitWait() {
        wait.until(ExpectedConditions.titleIs("Hillel Qauto"));
        System.out.println("Step1: Page title received: " + driver.getTitle());

        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, "Hillel Qauto", "Title does not match with expected value");
        System.out.println("Step2: Page title matches with expected value.");
    }

    @Test(priority = 2, description = "Verify that 'My profile' button is found")
    public void checkGuestFormUseJavaScriptExecutor() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Guest log in']")));
        System.out.println("Step1: 'Guest Log in' button is found");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginButton);
        System.out.println("Step2: Click on 'Guest log in' button is done");

        WebElement myProfileButton = driver.findElement(By.cssSelector("button.user-nav_toggle"));
        Assert.assertTrue(myProfileButton.getText().trim().equals("My profile"), "The button text not equal 'My profile'");
        Assert.assertTrue(myProfileButton.isDisplayed(), "The 'My profile' button is not displayed.");
        System.out.println("Step3: Found 'My profile' button");

    }

    @Test(priority = 3, description = "Verify that 'Add car' button is active and available for click")
    public void checkAddCarButtonUseExplicitWait() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Guest log in']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", loginButton);
        System.out.println("Step1: Click on 'Guest log in' button is done");
        WebElement addCarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add car']")));
        System.out.println("Step2: 'Add car' button is active and clickable");

       /* boolean isDisplayed = addCarButton.isDisplayed();
        System.out.println("Step3: 'Add car' button is displayed = " + isDisplayed );
        Assert.assertTrue(isDisplayed , "Step3: 'Add car' button is not displayed");

        boolean isEnabled = addCarButton.isEnabled();
        System.out.println("Step4: 'Add car' button is enabled = " + isEnabled);
        Assert.assertTrue(isEnabled, "Step4: 'Add car' button is not enabled.");*/
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        System.out.println("Browser closed");
    }


}
