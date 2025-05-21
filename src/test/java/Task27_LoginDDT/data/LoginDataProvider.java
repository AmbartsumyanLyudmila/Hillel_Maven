package Task27_LoginDDT.data;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "authFailData")
    public static Object[][] invalidCredentialsProvider() {
        return new Object[][]{
                {"test@hillel.ua", "1111"},
                {"test@hillel.ua", "1234"}
        };
    }
}