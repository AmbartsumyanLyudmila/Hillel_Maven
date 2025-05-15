package Task26_Selenide_PageObjects.pages;

import static com.codeborne.selenide.Selenide.$x;

public class GaragePage {

    public void clickAddCarButton() {
        $x("//button[contains(text(), 'Add car')]").click();
    }

    public void addCar(String brand, String model, String mileage) {
        $x("//select[@id='addCarBrand']").selectOption(brand);
        $x("//select[@id='addCarModel']").selectOption(model);
        $x("//input[@id='addCarMileage']").setValue(mileage);
        $x("//button[@class='btn btn-primary' and text()='Add']").click();
    }
}