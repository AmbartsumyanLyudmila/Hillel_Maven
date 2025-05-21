package Task31_HTTPClient;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIBrandsGetTest {

    @Test(priority = 1, description = "Check that response body contain brand=Audi with id=1 ")
    public void brandsResponseCheck() {
        SoftAssert softAssert = new SoftAssert();

        try {
            String url = "https://qauto.forstudy.space/api/cars/brands";
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();
            System.out.println("Response:");
            System.out.println("Status code: " + statusCode);
            System.out.println("Body:" + responseBody);
            System.out.println("--------------------------------");

            softAssert.assertEquals(statusCode, 200, "Response code should be 200");

            JSONObject json = new JSONObject(response.body());
            JSONArray brands = json.getJSONArray("data");

            boolean foundAudi = false;

            for (int i = 0; i < brands.length(); i++) {
                JSONObject brand = brands.getJSONObject(i);
                int id = brand.getInt("id");
                String title = brand.getString("title");

                if (id == 1 && "Audi".equalsIgnoreCase(title)) {
                    foundAudi = true;
                    break;
                }
            }

            softAssert.assertTrue(foundAudi, "Response body should contain brand Audi with id=1");


        } catch (ConnectException ce) {
            softAssert.fail("Cannot connect to server: " + ce.getMessage());
        } catch (Exception e) {
            softAssert.fail("Exception: " + e.getMessage());
        }

        softAssert.assertAll();
    }
}
