package Task28_Docker.dataProvider;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "authFailData")
    public static Object[][] authFailData() {
        return new Object[][] {
                {"test@hillel.ua", "1111"}
        };
    }

    @DataProvider(name = "browsers")
    public static Object[][] browsers() {
        return new Object[][] {
                {"chrome"},
                {"firefox"}
        };
    }
}
