package Task25_Allure.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
    public static WebDriver createDriver(String browser) {
        if (!browser.equalsIgnoreCase("chrome")) {
            throw new IllegalArgumentException("Browser not opened");
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}

