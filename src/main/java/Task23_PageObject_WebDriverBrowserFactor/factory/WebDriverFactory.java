package Task23_PageObject_WebDriverBrowserFactor.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                //System.out.println("Setup ChromeDriver");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                // System.out.println("Setup FirefoxDriver");
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}

