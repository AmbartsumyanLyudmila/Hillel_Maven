package Task25_Allure.tests;

import Task25_Allure.base.BaseTest;
import Task25_Allure.factory.WebDriverFactory;
import Task25_Allure.pages.GaragePage;
import Task25_Allure.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import io.qameta.allure.*;
import io.qameta.allure.Step;

import static io.qameta.allure.Allure.step;


@Epic("Garage Page")
@Feature("Add Car")
public class AllureTest extends BaseTest {

    @Test(priority = 1, description = "Verify that Add Car is worked on Chrome browser")
    @Owner("AQA")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that Add Car is worked on Chrome browser")
    @Link(name = "TFS Task", url = "https://tfs.companyName.com/search/tfsTestTask")
    @Story("Add car and validate data")
    public void testAddCarInChrome() {
        driver = WebDriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");

        try {
            loginAsGuest();
            addCar("Audi", "TT", "20");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SoftAssert softAssert = new SoftAssert();
        verifyCarNameDisplayed(softAssert);
        verifyCurrentDateInMileage(softAssert);
        verifyMileageValue(softAssert);
        verifyCarLogo(softAssert);
        softAssert.assertAll();
    }

    @Step("Login as Guest")
    private void loginAsGuest() throws InterruptedException {
        step("Step test");
        HomePage homePage = new HomePage(driver);
        homePage.clickGuestLogin();
    }

    @Step("Add Car with brand, model, miles")
    private void addCar(String brand, String model, String miles) throws InterruptedException {
        GaragePage garagePage = new GaragePage(driver);
        garagePage.clickAddCarButton();
        garagePage.addCar(brand, model, miles);
    }

    @Step("Verify that Audi TT is displayed")
    private void verifyCarNameDisplayed(SoftAssert softAssert) {
        try {
            WebElement addedCar = driver.findElement(By.xpath("//p[@class='car_name h2' and text()='Audi TT']"));
            softAssert.assertNotNull(addedCar, "Case 1: 'Audi TT' car is displayed on the page");
        } catch (Exception e) {
            softAssert.fail("Case 1: 'Audi TT' not found. " + e.getMessage());
        }
    }

    @Step("Verify that current date is displayed correctly")
    private void verifyCurrentDateInMileage(SoftAssert softAssert) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement mileageUpdate = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(@class, 'car_update-mileage')]")
            ));
            String mileageText = mileageUpdate.getText();
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            softAssert.assertTrue(mileageText.contains(currentDate),
                    "Case 2: current date is found = " + currentDate);
        } catch (Exception e) {
            softAssert.fail("Case 2: current date not found. " + e.getMessage());
        }
    }

    @Step("Verify that mileage value = 20")
    private void verifyMileageValue(SoftAssert softAssert) {
        try {
            WebElement milesInput = driver.findElement(By.xpath("//input[@formcontrolname='miles']"));
            softAssert.assertEquals(milesInput.getAttribute("value"), "20",
                    "Case 3: Miles is found and = 20 ");
        } catch (Exception e) {
            softAssert.fail("Case 3: Miles not equal 20. " + e.getMessage());
        }
    }

    @Step("Verify that the car logo is shown and the URL has \"audi.png\" at the end")
    private void verifyCarLogo(SoftAssert softAssert) {
        try {
            WebElement carLogo = driver.findElement(By.xpath("//img[contains(@src,'audi.png')]"));
            softAssert.assertTrue(carLogo.isDisplayed(), "Case 4: Car Logo is displayed");

            String imageUrl = carLogo.getAttribute("src");
            softAssert.assertTrue(imageUrl.endsWith("audi.png"),
                    "Case 5: url ended with audi.png");
        } catch (Exception e) {
            softAssert.fail("Case 4 & 5: Logo not found. " + e.getMessage());
        }
    }
}
