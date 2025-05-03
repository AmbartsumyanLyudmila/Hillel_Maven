package Task23_PageObject_WebDriverBrowserFactor.base;


import Task23_PageObject_WebDriverBrowserFactor.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;
    protected Actions actions;


    @BeforeMethod
    public void setup() {
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

