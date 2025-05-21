package Task30_RestAPI;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AssertLogoutResponseTest {
    @Test
    public void logoutGetResponseTest() {
        SoftAssert softAssert = new SoftAssert();

        try {
            URL url = new URL("https://qauto.forstudy.space/api/auth/logout");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            System.out.println("Successfully sent GET request");

            int responseCode = connection.getResponseCode();
            softAssert.assertEquals(responseCode, 200, "Response code should be 200");
            System.out.println("Response code= 200 and status: ok");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder responseBody = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                responseBody.append(inputLine);
            }
            in.close();

            String expectedBody = "{\"status\":\"ok\"}";
            softAssert.assertEquals(responseBody.toString(), expectedBody, "Response body should match");

        } catch (Exception e) {
            e.printStackTrace();
            softAssert.fail("Exception occurred: " + e.getMessage());
        }

        softAssert.assertAll();
    }
}
