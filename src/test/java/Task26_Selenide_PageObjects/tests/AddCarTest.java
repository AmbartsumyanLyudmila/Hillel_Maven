package Task26_Selenide_PageObjects.tests;

import Task26_Selenide_PageObjects.base.BaseTest;
import Task26_Selenide_PageObjects.pages.GaragePage;
import Task26_Selenide_PageObjects.pages.StartPage;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AddCarTest extends BaseTest {

    @Test(priority = 1, description = "Verify that Add Car is worked on Chrome browser")
    public void testAddCarInChrome() {
        open("https://guest:welcome2qauto@qauto.forstudy.space/");

        StartPage startPage = new StartPage();
        startPage.clickGuestLogin();

        GaragePage garagePage = new GaragePage();
        garagePage.clickAddCarButton();

        garagePage.addCar("Audi", "TT", "20");

        SoftAssert softAssert = new SoftAssert();

        SelenideElement addedCar = $x("//p[@class='car_name h2' and text()='Audi TT']");
        addedCar.shouldBe(visible);
        softAssert.assertTrue(addedCar.exists(), "Case 1: 'Audi TT' car is displayed on the page");

        SelenideElement mileageUpdate = $x("//p[contains(@class, 'car_update-mileage')]");
        mileageUpdate.shouldBe(visible);
        String mileageText = mileageUpdate.getText();
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        softAssert.assertTrue(mileageText.contains(currentDate), "Case 2: current date is found = " + currentDate);

        SelenideElement milesInput = $x("//input[@formcontrolname='miles']");
        milesInput.shouldBe(visible);
        softAssert.assertEquals(milesInput.getAttribute("value"), "20", "Case 3: Miles is found and = 20");

        SelenideElement carLogo = $x("//img[contains(@src,'audi.png')]");
        softAssert.assertTrue(carLogo.isDisplayed(), "Case 4: Car Logo is displayed");

        String imageUrl = carLogo.getAttribute("src");
        carLogo.shouldBe(visible);
        softAssert.assertTrue(imageUrl.endsWith("audi.png"), "Case 5: url ended with audi.png");

        softAssert.assertAll();
    }
}
