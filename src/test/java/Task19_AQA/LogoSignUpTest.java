package Task19_AQA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoSignUpTest {
    WebDriver driver;

    @BeforeMethod
    public void driverSet() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
    }

    @Test(priority = 1, description = "Verify that logo is visible on the page")
    public void logoTest() {
        try {
            WebElement logo = driver.findElement(By.xpath("//a[@class='header_logo']"));
            if (logo.isDisplayed()) {
                System.out.println("Test case1: Logo is displayed.");
            } else {
                System.out.println("Test case1: Logo does not displayed.");
                Assert.fail("Test case1: Logo is not displayed.");
            }
        } catch (Exception e) {
            Assert.fail("Test case1: Logo element was not found.");
        }
    }

    @Test(priority = 2, description = "Verify that background color of the 'Sign up' button is correct")
    public void signUpTest() {
        try {
            WebElement signUpButton = driver.findElement(By.xpath("//button[contains(text(),'Sign up')]"));

            String backgroundColor = signUpButton.getCssValue("background-color");

            if (backgroundColor.equals("rgba(2, 117, 216, 1)")) {
                System.out.println("Test case2: Background color of Sign up button is correct (#0275d8)");
            } else {
                System.out.println("Test case2: Background color of Sign up button is incorrect.");
                Assert.fail("Test case2: Background color of Sign up button is incorrect.");
            }
        } catch (Exception e) {
            System.out.println("Test case2: Sign up button was not found.");
            Assert.fail("Test case2: Sign up button element was not found.");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
