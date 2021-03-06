package serenity.zipcode;

import io.restassured.RestAssured;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

@Disabled
@SerenityTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class ZipToCityTest {

    //https://api.zippopotam.us/{{country}}/{{zipcode}}
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "https://api.zippopotam.us" ;
    }
    @AfterAll
    public static void cleanUp(){
        clear();
    }

    @Disabled
    @DisplayName("Zip 1. Testing 1 zip code and get the result")
    @Test
    public void test1ZipCode(){

        given()
                .pathParam("country","us")
                .pathParam("zipcode","63368").
        when()
                .get("/{country}/{zipcode}").
        then()
                .statusCode(200)
                .body("'post code'", is("63368"))
                .body("places[0].'place name'", is("O Fallon"))
        ;
    }


    @DisplayName("Zip 2. Testing multiple zipcodes and get the result")
    @ParameterizedTest
    @ValueSource(strings = {"32811", "32835", "63367", "63368", "10000"})
    public void testMultipleZipCodes(String zip){

        // run this parameterized test with 5 zipcodes
        // start with no external file
        // then add external csv file in separate test
        // System.out.println("zip = "+zip);

        given()
                .pathParam("country", "us")
                .pathParam("zipcode", zip).
        when()
                .get("/{country}/{zipcode}");

        Ensure.that("we got successful result", v-> v.statusCode(is(200)));

    }

    /**
     * {index} -->> to represent index
     * {arguments} -->>
     * @param zip
     */
    @Disabled
    @ParameterizedTest(name = "Interation {index} zipcode {arguments} : 😃 :")
    @ValueSource(strings = {"32811", "32835", "63367", "63368", "10000"})
    @Test
    public void testDisplayNameManipulation (String zip){ }


    @ParameterizedTest(name = "Iteration {index}, Country is {0}, Zipcode is {1}")
    @CsvFileSource(resources = "/country_zip.csv", numLinesToSkip = 1)
    public void testCountryZip(String country, int zip){

        given()
                .pathParam("country", country)
                .pathParam("zipcode", zip).
        when()
                .get("/{country}/{zipcode}");
    }

}
