package Task24_PageObjects_SaveFile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GaragePage {
    private WebDriver driver;

    private By instructionButton = By.xpath("//a[@routerlink='instructions']");
    private By instructionPageLoadedElement = By.xpath("//a[@class='instruction-link_download' and @href='https://qauto.forstudy.space/public/instructions/audi/tt/Front windshield wipers on Audi TT.pdf']");

    public GaragePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToInstructions() {
        driver.findElement(instructionButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(instructionPageLoadedElement));

    }
}
