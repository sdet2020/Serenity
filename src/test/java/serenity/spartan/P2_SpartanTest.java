package serenity.spartan;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.*;
import serenity.utility.SpartanUtil;


import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.is;

@Disabled
@SerenityTest
@Tag("spartan")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class P2_SpartanTest extends BaseClass {

    @DisplayName("P2.1 Testing GET /api/spartans/102 Endpoint")
    @Test
    public void testingSpartansEndPoint(){

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/3").
        then()
                .log().all();

        // Serenity's way of generating some steps for verification
        Ensure.that("spartan 102 is retrieved from dataBase",
                response-> response
                        .statusCode(200)
                        .contentType(ContentType.JSON))
                .andThat("name is Fidole",
                        name-> name.body("name", is("Fidole")))
                .andThat("gender is Female",
                        gender-> gender.body("gender", is("Male")))
                .andThat("phone is '1231231231'",
                        phone-> phone.body("phone", is(6105035233l))
                );
    }

    @DisplayName("P2.2 Admin user should be able to Add a Spartan")
    @Test
    public void testAdd1Data(){
        Map<String, Object> payload = SpartanUtil.getRandomSpartanRequestPayload();

                given()
                        .contentType(ContentType.JSON)
                        .body(payload).
                when()
                        .post("/spartans").
                then()
                        .log().all();


        Ensure.that("POST was successfull",
                v-> v.statusCode(is(201)))
                .andThat("name is matching ",
                        v-> v.body("data.name", is(payload.get("name"))))
                .andThat("gender is matching ",
                        v-> v.body("data.gender", is(payload.get("gender"))))
                .andThat("phone is matching ",
                        v-> v.body("data.phone", is(payload.get("phone"))));

        //System.out.println("lastResponse().jsonPath().getInt(\"data.id\") = " + lastResponse().jsonPath().getInt("data.id"));
    }

    @DisplayName("P2.3 Admin Should be able to read single data")
    @Test
    public void getOneData(){
        //int newID = lastResponse().jsonPath().getInt("data.id");

        given()
                .accept(ContentType.JSON)
                .pathParam("id", 3).
        when()
                .get("/spartans/{id}").
        then()
                .log().all();

        Ensure.that("we can access newly generated data",
                v-> v.statusCode(is(200)));
    }

    @DisplayName("P2.4 Admin Should be able to delete single data")
    @Test
    public void testDeleteOneData(){

        // capture the id from last get request
        // int myId = lastResponse().jsonPath().getInt("id") ;

        given()
                .pathParam("id", 4).
        when()
                .delete("/spartans/{id}" ) ;

        Ensure.that("Request is successful",
                v -> v.statusCode(204) ) ;

        // send another get request to make sure you get 404
        given()
                .pathParam("id", 4).
        when()
                .get("/spartans/{id}" ) ;

        Ensure.that("Delete was successful, Can not find data anymore",
                v -> v.statusCode(404) ) ;

    }
}
