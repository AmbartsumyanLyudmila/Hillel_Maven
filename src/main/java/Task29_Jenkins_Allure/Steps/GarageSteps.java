package Task29_Jenkins_Allure.Steps;

import Task29_Jenkins_Allure.pages.GaragePage;
import Task29_Jenkins_Allure.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class GarageSteps {
    private WebDriver driver;

    public GarageSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Login as Guest")
    public void loginAsGuest() {
        new HomePage(driver).clickGuestLogin();
    }

    @Step("Add car: {brand} {model} with mileage: {miles}")
    public void addCar(String brand, String model, String miles) {
        new GaragePage(driver).addCar(brand, model, miles);
    }
}