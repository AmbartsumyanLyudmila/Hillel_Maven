package Task26_Selenide_PageObjects.tests;

import Task26_Selenide_PageObjects.base.BaseTest;
import Task26_Selenide_PageObjects.factory.WebDriverFactory;
import Task26_Selenide_PageObjects.pages.GaragePage;
import Task26_Selenide_PageObjects.pages.StartPage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class AddCarTest extends BaseTest {

    @Test(priority = 1, description = "Verify that Add Car is worked on Chrome browser")
    public void testAddCarInChrome() throws InterruptedException {
        WebDriverFactory.createDriver();
        StartPage startPage = new StartPage(driver);
        startPage.clickGuestLogin();

        GaragePage garagePage = new GaragePage(driver);
        garagePage.clickAddCarButton();
        garagePage.addCar("Audi", "TT", "20");

        SoftAssert softAssert = new SoftAssert();

        SelenideElement addedCar = $(By.xpath("//p[@class='car_name h2' and text()='Audi TT']"));
        softAssert.assertTrue(addedCar.exists(), "Case 1: 'Audi TT' car is displayed on the page");

        SelenideElement mileageUpdate = $(By.xpath("//p[contains(@class, 'car_update-mileage')]"));
        String mileageText = mileageUpdate.getText();
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        softAssert.assertTrue(mileageText.contains(currentDate), "Case 2: current date is found = " + currentDate);

        SelenideElement milesInput = $(By.xpath("//input[@formcontrolname='miles']"));
        softAssert.assertEquals(milesInput.getAttribute("value"), "20", "Case 3: Miles is found and = 20");

        SelenideElement carLogo = $(By.xpath("//img[contains(@src,'audi.png')]"));
        softAssert.assertTrue(carLogo.isDisplayed(), "Case 4: Car Logo is displayed");

        String imageUrl = carLogo.getAttribute("src");
        softAssert.assertTrue(imageUrl.endsWith("audi.png"), "Case 5: url ended with audi.png");

        softAssert.assertAll();
    }
}
