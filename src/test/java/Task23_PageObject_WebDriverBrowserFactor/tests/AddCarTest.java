package Task23_PageObject_WebDriverBrowserFactor.tests;

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


public class AddCarTest extends BaseTest {

    @Test(priority = 1, description = "Verify that Add Car is worked on Chrome browser")
    public void testAddCarInChrome() {
        driver = WebDriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");

        try {
            StartPage homePage = new StartPage(driver);
            homePage.clickGuestLogin();

            GaragePage garagePage = new GaragePage(driver);
            garagePage.clickAddCarButton();
            garagePage.addCar("Audi", "TT", "20");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SoftAssert softAssert = new SoftAssert();


        try {
            WebElement addedCar = driver.findElement(By.xpath("//p[@class='car_name h2' and text()='Audi TT']"));
            softAssert.assertNotNull(addedCar, "Case1: 'Audi TT' car is displayed on the page");
        } catch (Exception e) {
            softAssert.fail("Case1: 'Audi TT' not found " + e.getMessage());
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement mileageUpdate = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(@class, 'car_update-mileage')]")
            ));
            String mileageText = mileageUpdate.getText();
            System.out.println("Case2: CurrentDate " + mileageText);
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            softAssert.assertTrue(mileageText.contains(currentDate),
                    "Case 2: current date is found = " + currentDate);
        } catch (Exception e) {
            softAssert.fail("Case 2: current date not found" + e.getMessage());
        }

        try {
            WebElement milesInput = driver.findElement(By.xpath("//input[@formcontrolname='miles']"));
            softAssert.assertEquals(milesInput.getAttribute("value"), "20",
                    "Case 3: Miles is found and = 20 ");
        } catch (Exception e) {
            softAssert.fail("Case 3: Miles not equal 20 " + e.getMessage());
        }

        try {
            WebElement carLogo = driver.findElement(By.xpath("//img[contains(@src,'audi.png')]"));
            softAssert.assertTrue(carLogo.isDisplayed(), "Case 4: Car Logo is displayed");

            String imageUrl = carLogo.getAttribute("src");
            softAssert.assertTrue(imageUrl.endsWith("audi.png"),
                    "Case 5: url ended with audi.png");
        } catch (Exception e) {
            softAssert.fail("Case 4 & 5: Logo not found" + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify that Add Car is worked on Firefox browser")
    public void testAddCarInFirefox() throws InterruptedException {
        driver = WebDriverFactory.createDriver("firefox");
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        Thread.sleep(3000);
        System.out.println("Firefox test");


        try {
            StartPage homePage = new StartPage(driver);
            homePage.clickGuestLogin();

            GaragePage garagePage = new GaragePage(driver);
            garagePage.clickAddCarButton();
            garagePage.addCar("Audi", "TT", "20");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SoftAssert softAssert = new SoftAssert();


        try {
            WebElement addedCar = driver.findElement(By.xpath("//p[@class='car_name h2' and text()='Audi TT']"));
            softAssert.assertNotNull(addedCar, "Case1: 'Audi TT' car is displayed on the page");
        } catch (Exception e) {
            softAssert.fail("Case1: 'Audi TT' not found " + e.getMessage());
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement mileageUpdate = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(@class, 'car_update-mileage')]")
            ));
            String mileageText = mileageUpdate.getText();
            System.out.println("Case2: CurrentDate " + mileageText);
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            softAssert.assertTrue(mileageText.contains(currentDate),
                    "Case 2: current date is found = " + currentDate);
        } catch (Exception e) {
            softAssert.fail("Case 2: current date not found" + e.getMessage());
        }

        try {
            WebElement milesInput = driver.findElement(By.xpath("//input[@formcontrolname='miles']"));
            softAssert.assertEquals(milesInput.getAttribute("value"), "20",
                    "Case 3: Miles is found and = 20 ");
        } catch (Exception e) {
            softAssert.fail("Case 3: Miles not equal 20 " + e.getMessage());
        }

        try {
            WebElement carLogo = driver.findElement(By.xpath("//img[contains(@src,'audi.png')]"));
            softAssert.assertTrue(carLogo.isDisplayed(), "Case 4: Car Logo is displayed");

            String imageUrl = carLogo.getAttribute("src");
            softAssert.assertTrue(imageUrl.endsWith("audi.png"),
                    "Case 5: url ended with audi.png");
        } catch (Exception e) {
            softAssert.fail("Case 4 & 5: Logo not found" + e.getMessage());
        }
        softAssert.assertAll();
    }
}


