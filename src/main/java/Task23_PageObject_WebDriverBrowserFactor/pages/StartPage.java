package Task23_PageObject_WebDriverBrowserFactor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage {
    private WebDriver driver;

    private By guestLoginButton =By.xpath("//button[text()='Guest log in']");

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickGuestLogin() {
        WebElement button = driver.findElement(guestLoginButton);
        button.click();
    }


}


