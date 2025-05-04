package Task24_PageObjects_SaveFile.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import Task24_PageObjects_SaveFile.factory.WebDriverFactory;
import Task24_PageObjects_SaveFile.pages.StartPage;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = WebDriverFactory.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        StartPage startPage = new StartPage(driver);
        startPage.clickGuestLogin();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

