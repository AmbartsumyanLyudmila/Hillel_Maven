package Task29_Jenkins_Allure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GaragePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addCarButton = By.xpath("//button[contains(text(), 'Add car')]");
    private By carBrandOption = By.xpath("//select[@id='addCarBrand']/option[text()='Audi']");
    private By carModelOption = By.xpath("//select[@id='addCarModel']/option[text()='TT']");
    private By carMileageInput = By.id("addCarMileage");
    private By submitButton = By.xpath("//button[@class='btn btn-primary' and text()='Add']");

    public GaragePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addCar(String brand, String model, String mileage) {
        wait.until(ExpectedConditions.elementToBeClickable(addCarButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(carBrandOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(carModelOption)).click();
        driver.findElement(carMileageInput).sendKeys(mileage);
        driver.findElement(submitButton).click();
    }
}
