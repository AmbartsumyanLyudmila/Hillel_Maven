package Task31_2_RestAssured.tests;

import Task31_2_RestAssured.utils.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetBrandsAPITest {

    @BeforeClass
    public void setUp() {
        TestConfig.setup();
    }

    @Test(priority = 1, description = "Check that response body contain brand=Audi with id=1 ")
    public void CheckBrandTest() {
        Response response = RestAssured
                .given()
                .when()
                .get();

        response.then().statusCode(200);

        System.out.println("Response: Status code = " + response.statusCode() + ", Body = " + response.getBody().asString());

        response.then()
                .body("data.find { it.id == 1 && it.title == 'Audi' }", notNullValue());

    }
}