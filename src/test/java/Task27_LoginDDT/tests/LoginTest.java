package Task27_LoginDDT.tests;

import Task27_LoginDDT.base.BaseTest;
import Task27_LoginDDT.data.LoginDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "authFailData", dataProviderClass = LoginDataProvider.class)
    public void authFailDataTest(String email, String password) {
        System.out.println("Enter Email: " + email);
        System.out.println("Enter Password: " + password);

        WebElement signInButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        signInButton.click();


        driver.findElement(By.xpath("//input[@id='signinEmail']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='signinPassword']")).sendKeys(password);


        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(@class, 'alert-danger') and text()='Wrong email or password']")));
        Assert.assertEquals(alert.getText().trim(), "Wrong email or password");
    }
}
