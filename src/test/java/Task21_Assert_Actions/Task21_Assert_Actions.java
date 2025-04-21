package Task21_Assert_Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task21_Assert_Actions {
    WebDriver driver;
    Actions actions;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        actions = new Actions(driver);
    }

    @Test(priority = 1, description = "Test case1: Verify that logo is displayed ")
    public void logoTest() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='header_logo']")));
            actions.moveToElement(logo).perform();
            Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");
            System.out.println("Test case 1: Logo is displayed.");
        } catch (Exception e) {
            Assert.fail("Test case 1: Logo is not displayed.");
        }
    }

    @Test(priority = 2, description = "Verify that background color of the 'Sign up' button is correct")
    public void signUpButtonBackgroundColorTest() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement signUpButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(text(),'Sign up')]")));
            Assert.assertTrue(signUpButton.isEnabled(), "Sign up button is not enabled");
            //actions.moveToElement(signUpButton).perform(); for check incorrect background color

            String actualColor = signUpButton.getCssValue("background-color");
            String expectedColor = "rgba(2, 117, 216, 1)";

            Assert.assertEquals(actualColor, expectedColor, "Test case2: Background color of Sign up button is wrong");
            System.out.println("Test case 2: Background color of Sign up button is correct");
        } catch (Exception e) {
            Assert.fail("Test case2: Something wrong");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
