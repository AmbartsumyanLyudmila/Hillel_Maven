package Task26_Selenide_PageObjects.pages;

import static com.codeborne.selenide.Selenide.$x;

public class StartPage {

    public void clickGuestLogin() {
        $x("//button[text()='Guest log in']").click();
    }

}
