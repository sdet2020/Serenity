package serenity.spartan;

//import static io.restassured.RestAssured.*; // instead of this we will make the one below

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.*;

import static net.serenitybdd.rest.SerenityRest.when;
import static org.hamcrest.Matchers.is;

@Disabled
@SerenityTest
@Tag("spartan")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class P1_SpartanTest extends BaseClass {

    @DisplayName("P1.1 Test GET /api/hello Endpoint Text")
    @Test
    public void test1(){

        when()
                .get("/hello").
        then()
                .statusCode(is(200))
                .contentType(ContentType.TEXT)
                .log().all();

        Ensure.that("contentType is TEXT",
                verify-> verify.contentType(ContentType.TEXT))
        .andThat("dispayed text is: \"Hello from Sparta\"",
                verify-> verify.body(is("Hello from Sparta")));
    }
}
