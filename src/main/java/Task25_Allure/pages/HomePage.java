package Task25_Allure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    private By guestLoginButton =By.xpath("//button[text()='Guest log in']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickGuestLogin() {
        WebElement button = driver.findElement(guestLoginButton);
        button.click();
    }


}


