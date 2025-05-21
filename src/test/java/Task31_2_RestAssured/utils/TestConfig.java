package Task31_2_RestAssured.utils;

import io.restassured.RestAssured;

public class TestConfig {
    public static void setup() {
        RestAssured.baseURI = "https://qauto.forstudy.space";
        RestAssured.basePath = "/api/cars/brands";
    }
}
