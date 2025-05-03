package Task23_PageObject_WebDriverBrowserFactor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GaragePage {
    private By addCarButton = By.xpath("//button[contains(text(), 'Add car')]");
    private By carBrandDropdown = By.xpath("//select[@id='addCarBrand']/option[text()='Audi']");
    private By carBrandOption = By.xpath("//select[@id='addCarBrand']/option[text()='Audi']");
    private By carModelDropdown = By.name("carModelId");
    private By carModelOption = By.xpath("//select[@id='addCarModel']/option[text()='TT']");
    private By carMileageInput = By.xpath("//input[@id='addCarMileage']");
    private By submitButton = By.xpath("//button[@class='btn btn-primary' and text()='Add']");
    private WebDriver driver;


    public GaragePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddCarButton() throws InterruptedException {
        WebElement button = driver.findElement(addCarButton);
        button.click();
        Thread.sleep(1000);
    }

    public void addCar(String brand, String model, String mileage) throws InterruptedException {
        WebElement brandDropdown = driver.findElement(carBrandDropdown);
        brandDropdown.click();
        WebElement brandOption = driver.findElement(carBrandOption);
        brandOption.click();
        Thread.sleep(1000);

        WebElement modelDropdown = driver.findElement(carModelDropdown);
        modelDropdown.click();
        WebElement modelOption = driver.findElement(carModelOption);
        modelOption.click();
        Thread.sleep(1000);

        WebElement mileageInput = driver.findElement(carMileageInput);
        mileageInput.sendKeys(mileage);
        Thread.sleep(1000);

        WebElement addButton = driver.findElement(submitButton);
        addButton.click();
        Thread.sleep(1000);
    }

}
