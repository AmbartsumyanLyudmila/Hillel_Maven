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
import java.util.Set;

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
            System.out.println("Found iframe → " + iframe.getAttribute("src"));

            driver.switchTo().frame(iframe);
            //System.out.println("Switched to iframe successfully");

            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("a.ytp-title-link")));

            String actualTitle = title.getText();
            System.out.println("Found title → \"" + actualTitle + "\"");
            String expectedTitle = "Як потрапити у майбутнє? Трансформація навчання.";

            Assert.assertEquals(actualTitle, expectedTitle, "Test case1: Title doesn’t equals to the expected result");

            //System.out.println("Test case1: YouTube iframe video title is correct");
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            Assert.fail("Test case1: Something went wrong");
        }
    }


    @Test(priority = 2, description = "Verify social media links open correct pages and there are exactly 5 icons")
    public void testSocialMediaLinks() {
        try {
            driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
            driver.manage().window().maximize();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            System.out.println("Scrolled down to the footer");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            List<WebElement> socialIcons = wait.until(ExpectedConditions
                    .presenceOfAllElementsLocatedBy(By.cssSelector("div.contacts_socials.socials a.socials_link")));
            int actualResult = socialIcons.size();
            int expectedResult = 5;
            System.out.println("Found social icons = " + actualResult);

            Assert.assertEquals(actualResult, expectedResult,
                    "Test case2: Social network block doesn’t contain 5 items");

            boolean allUrlsCorrect = true;

            for (int i = 0; i < socialIcons.size(); i++) {
                try {
                    List<WebElement> icons = driver.findElements(By.cssSelector("div.contacts_socials.socials a.socials_link"));
                    WebElement icon = icons.get(i);

                    String iconUrl = icon.getAttribute("href");
                    // System.out.println("Clicking on icon");

                    icon.click();
                    // Thread.sleep(2000);

                    Set<String> windows = driver.getWindowHandles();
                    if (windows.size() == 1) {
                        System.out.println("Test case2: New tab did not open after clicking ");
                        allUrlsCorrect = false;
                        continue;
                    }

                    String newTab = (String) windows.toArray()[1];
                    driver.switchTo().window(newTab);

                    String url = driver.getCurrentUrl();
                    // System.out.println("Opened URL: " + url);

                    if (url.contains("youtube.com") && url.contains("consent")) {
                        System.out.println("YouTube consent page opened: " + url);
                    } else if (url.contains("linkedin.com") && url.contains("authwall")) {
                        System.out.println("LinkedIn authorization page opened: " + url);
                    } else if (url.contains("facebook.com")) {
                        System.out.println("Facebook URL opened: " + url);
                    } else if (url.contains("twitter.com")) {
                        System.out.println("Twitter URL opened: " + url);
                    } else if (url.contains("instagram.com")) {
                        System.out.println("Instagram URL opened: " + url);
                    } else if (url.contains("t.me")) {
                        System.out.println("Telegram URL opened: " + url);
                    } else {
                        System.out.println("Incorrect URL of Social network: " + url);
                        allUrlsCorrect = false;
                    }

                    driver.close();
                    driver.switchTo().window((String) windows.toArray()[0]);

                } catch (Exception e) {
                    System.out.println("Error while checking social icon ");
                    allUrlsCorrect = false;
                }
            }

            Assert.assertTrue(allUrlsCorrect,
                    "Test case2: Incorrect url of Social network");

        } catch (Exception e) {
            Assert.fail("Test case2: Something went wrong");
        }
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}



