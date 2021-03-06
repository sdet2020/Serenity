package serenity.spartan;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class BaseClass {

    @BeforeAll
    public static void setup(){
        baseURI = "http://54.211.84.13:8000";
        basePath = "/api";
        RestAssured.given().auth().basic("admin", "admin");

    }

    @AfterAll
    public static void destroy(){
        RestAssured.reset();
        SerenityRest.clear();
    }

}
