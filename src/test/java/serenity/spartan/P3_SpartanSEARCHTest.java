package serenity.spartan;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.*;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.*;

@Disabled
@SerenityTest
@Tag("spartan")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class P3_SpartanSEARCHTest extends BaseClass {

    @DisplayName("P3.1 Authenticated user should be able to search")
    @Test
    public void testSearch(){
        given()
                .queryParam("nameContains", "a").
        when()
                .get("/spartans/search").
        then()
                .log().all();

        Ensure.that("request was successful",
                v-> v.statusCode(is(200)))
        .andThat("we got JSON result",
                v-> v.contentType(ContentType.JSON));

        // make sure all names contain a
        Ensure.that("all names contain 'a'",
                v-> v.body("content.name", everyItem(containsString("a"))));
    }
}
