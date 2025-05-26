package Task28_Docker.dataProvider;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "authFailDataWithBrowsers")
    public static Object[][] authFailDataWithBrowsers() {
        return new Object[][]{
                {"test@hillel.ua", "1111", "chrome"},
                {"test@hillel.ua", "1111", "firefox"}
        };
    }
}
