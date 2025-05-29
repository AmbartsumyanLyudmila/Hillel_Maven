package Task29_Jenkins_Allure.base.tests;

import Task29_Jenkins_Allure.Steps.GarageSteps;
import Task29_Jenkins_Allure.base.base.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AllureTest extends BaseTest {

    @Epic("Garage Page")
    @Feature("Add Car")
    @Owner("AQA")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Add a car and validate it appears correctly")

    @Test(description = "Verify that Add Car works in Chrome")
    public void testAddCarInChrome() {
        GarageSteps steps = new GarageSteps(driver);

        steps.loginAsGuest();
        steps.addCar("Audi", "TT", "20");
        //Thread.sleep(2000);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[@class='car_name h2' and text()='Audi TT']")
                )
        );
        SoftAssert softAssert = new SoftAssert();
        verifyCarNameDisplayed(softAssert);
        verifyCurrentDateInMileage(softAssert);
        verifyMileageValue(softAssert);
        verifyCarLogo(softAssert);
        softAssert.assertAll();
    }

    @Step("Verify car name is displayed as Audi TT")
    private void verifyCarNameDisplayed(SoftAssert softAssert) {
        try {
            WebElement car = driver.findElement(By.xpath("//p[@class='car_name h2' and text()='Audi TT']"));
            softAssert.assertNotNull(car, "Case 1: Car name is displayed");
        } catch (Exception e) {
            softAssert.fail("Case 1: Car name not found. " + e.getMessage());
        }
    }

    @Step("Verify mileage update contains today's date")
    private void verifyCurrentDateInMileage(SoftAssert softAssert) {
        try {
            String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            WebElement mileage = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//p[contains(@class, 'car_update-mileage')]")
                    ));
            softAssert.assertTrue(mileage.getText().contains(expectedDate),
                    "Case 2: Date matches current date " + expectedDate);
        } catch (Exception e) {
            softAssert.fail("Case 2: Current date not found. " + e.getMessage());
        }
    }

    @Step("Verify mileage value equals 20")
    private void verifyMileageValue(SoftAssert softAssert) {
        try {
            WebElement mileageInput = driver.findElement(By.xpath("//input[@formcontrolname='miles']"));
            softAssert.assertEquals(mileageInput.getAttribute("value"), "20",
                    "Case 3: Mileage is 20");
        } catch (Exception e) {
            softAssert.fail("Case 3: Mileage is incorrect. " + e.getMessage());
        }
    }

    @Step("Verify Audi logo is shown correctly")
    private void verifyCarLogo(SoftAssert softAssert) {
        try {
            WebElement logo = driver.findElement(By.xpath("//img[contains(@src,'audi.png')]"));
            softAssert.assertTrue(logo.isDisplayed(), "Case 4: Logo is displayed");
            softAssert.assertTrue(logo.getAttribute("src").endsWith("audi.png"),
                    "Case 5: Logo src ends with audi.png");
        } catch (Exception e) {
            softAssert.fail("Case 4 & 5: Logo issue. " + e.getMessage());
        }
    }
}
