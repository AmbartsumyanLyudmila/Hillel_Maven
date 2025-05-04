package Task24_PageObjects_SaveFile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InstructionPage {
    private WebDriver driver;

    private By downloadFile = By.xpath("//a[@class='instruction-link_download' and @href='https://qauto.forstudy.space/public/instructions/audi/tt/Front windshield wipers on Audi TT.pdf']");

    public InstructionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void downloadInstruction() {
        driver.findElement(downloadFile).click();
    }
    //====================================================

    private By carSelectDropDown = By.xpath("//button[@id='brandSelectDropdown']");
    private By carsList = By.xpath("//ul[@class='brand-select-dropdown_menu dropdown-menu show']//li");

    public void clickCarSelectionButton() {
        driver.findElement(carSelectDropDown).click();
    }

    public List<String> getAvailableCars() {
        List<WebElement> carsElements = driver.findElements(carsList);
        List<String> carNames = new ArrayList<>();

        for (WebElement car : carsElements) {
            carNames.add(car.getText().trim());
        }
        System.out.println("Available cars list: ");
        for (String car : carNames) {
            System.out.println(car);
        }

        return carNames;
    }


}
