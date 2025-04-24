package Task22_iFrame;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import java.util.List;

public class FrameTest {
    WebDriver driver;
    Actions actions;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        actions = new Actions(driver);
    }

    @Test(priority = 1, description = "Verify that the text title matches the expected result")
    public void frameTitleTest() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("iframe[src*='znjjC0Iw8Wc']")));
            System.out.println("Step1: Found iframe → " + iframe.getAttribute("src"));

            driver.switchTo().frame(iframe);
            System.out.println("Step2: Switched to iframe successfully");

            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("a.ytp-title-link")));

            String actualTitle = title.getText();
            System.out.println("Step3: Found title → \"" + actualTitle + "\"");
            String expectedTitle = "Як потрапити у майбутнє? Трансформація навчання.";

            Assert.assertEquals(actualTitle, expectedTitle, "Test case1: Title doesn’t equals to the expected result");

            System.out.println("Test case1: YouTube iframe video title is correct");
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            Assert.fail("Test case1: Something went wrong");
        }
    }

    @Test(priority = 2, description = "Verify that footers social icons")
    public void verifyFooterSocialIcons() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            System.out.println("Scrolled down to the footer");
            //Thread.sleep(1000);

            List<WebElement> socialIcons = wait.until(ExpectedConditions
                    .presenceOfAllElementsLocatedBy(By.cssSelector("div.contacts_socials.socials a.socials_link")));
            System.out.println("Found social icons = " + socialIcons.size());

            int actualResult = socialIcons.size();
            int expectedResult = 5;
            System.out.println("Test case2: Social network block contains 5 items");

            Assert.assertEquals(actualResult, expectedResult, "Test case2: Social network block doesn’t contain 5 items");
        }catch (Exception e) {
            Assert.fail("Test case2: Test failed at step: verifyFooterSocialIconsCount");
        }}



    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}



