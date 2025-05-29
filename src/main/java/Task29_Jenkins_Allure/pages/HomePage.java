package Task29_Jenkins_Allure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By guestLoginButton = By.xpath("//button[text()='Guest log in']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickGuestLogin() {
        driver.findElement(guestLoginButton).click();
    }
}
