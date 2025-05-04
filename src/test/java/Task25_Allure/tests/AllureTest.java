package Task25_Allure.tests;

import Task23_PageObject_WebDriverBrowserFactor.base.BaseTest;
import Task23_PageObject_WebDriverBrowserFactor.factory.WebDriverFactory;
import Task23_PageObject_WebDriverBrowserFactor.pages.GaragePage;
import Task23_PageObject_WebDriverBrowserFactor.pages.StartPage;
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


@Epic("Garage Functionality")
@Feature("Add Car Feature")
public class AllureTest extends BaseTest {

    @Test(priority = 1)
    @Owner("AQA")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Перевірка, що додавання авто працює в Chrome браузері")
    @Link(name = "JIRA Task", url = "https://jira.example.com/browse/QA-125")
    @Story("Add car and validate its display and data")
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

    @Step("Авторизація як гість")
    private void loginAsGuest() throws InterruptedException {
        StartPage homePage = new StartPage(driver);
        homePage.clickGuestLogin();
    }

    @Step("Додавання авто {brand} {model} з пробігом {miles}")
    private void addCar(String brand, String model, String miles) throws InterruptedException {
        GaragePage garagePage = new GaragePage(driver);
        garagePage.clickAddCarButton();
        garagePage.addCar(brand, model, miles);
    }

    @Step("Перевірка, що назва авто 'Audi TT' відображається")
    private void verifyCarNameDisplayed(SoftAssert softAssert) {
        try {
            WebElement addedCar = driver.findElement(By.xpath("//p[@class='car_name h2' and text()='Audi TT']"));
            softAssert.assertNotNull(addedCar, "Case 1: 'Audi TT' car is displayed on the page");
        } catch (Exception e) {
            softAssert.fail("Case 1: 'Audi TT' not found. " + e.getMessage());
        }
    }

    @Step("Перевірка, що у пробігу відображається поточна дата")
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

    @Step("Перевірка, що поле пробігу містить значення 20")
    private void verifyMileageValue(SoftAssert softAssert) {
        try {
            WebElement milesInput = driver.findElement(By.xpath("//input[@formcontrolname='miles']"));
            softAssert.assertEquals(milesInput.getAttribute("value"), "20",
                    "Case 3: Miles is found and = 20 ");
        } catch (Exception e) {
            softAssert.fail("Case 3: Miles not equal 20. " + e.getMessage());
        }
    }

    @Step("Перевірка, що відображається логотип авто та URL закінчується на audi.png")
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
