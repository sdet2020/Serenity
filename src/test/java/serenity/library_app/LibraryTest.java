package serenity.library_app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serenity.utility.ConfigReader;

import static net.serenitybdd.rest.SerenityRest.*;


@SerenityTest
public class LibraryTest {

    @Steps
    ConfigReader conf ; // this created an object of ConfigReader class

// Since ConfigReader conf is non-static variable
// and @BeforeAll is static and cannot accept non-static variable
// we have to use @BeforeEach

    @BeforeEach
    public void setUpEach(){
        RestAssured.baseURI  = conf.getProperty("base.url") ;
        RestAssured.basePath = conf.getProperty("base.path") ;
    }


    @AfterAll
    public static void tearDown(){
        RestAssured.reset();
        SerenityRest.clear();
    }

    @Test
    public void testLogin(){
        given()
                .log().all()
                .contentType( ContentType.URLENC  )
                .formParam("email", conf.getProperty("librarian.username"))
                .formParam("password",conf.getProperty("librarian.password")).
        when()
                .post("/login") ;

        Ensure.that("Getting Successful Result",
                vRes -> vRes.statusCode(200)
        ) ;
    }


    @Test
    public void testingReadingConfigFile(){

        System.out.println("conf.getProperty(\"base.url\") = "
                + conf.getProperty("base.url"));
        System.out.println("conf.getProperty(\"librarian.username\") = "
                + conf.getProperty("librarian.username"));

    }


}
