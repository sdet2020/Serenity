package serenity.library_app;

import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

@SerenityTest
public class Library_Test {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "/rest/v1";

    }

    @AfterAll
    public static void tearDown(){
        RestAssured.reset();
        clear();
    }

    @Test
    public void testBookCategories(){

        Response response =
        given()
                .accept(ContentType.JSON)
                .header("x-library-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjA5MCIsImZ1bGxfbmFtZSI6IlRlc3QgTGlicmFyaWFuIDY5IiwiZW1haWwiOiJsaWJyYXJpYW42OUBsaWJyYXJ5IiwidXNlcl9ncm91cF9pZCI6IjIifSwiaWF0IjoxNjA5ODIwMTQ3LCJleHAiOjE2MTI0MTIxNDd9.tnboSHrwWP3VjEvSsuBaUNx5zOu2cLvKQzOQLLxZX3M").
        when()
                .get("/get_book_categories").
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all()
                .extract().response();

        Ensure.that("first category is \"Action and Adventure\"",
                v-> v.body("name[0]", is("Action and Adventure")));

        JsonPath jp = response.jsonPath();

        List<String> bc = jp.getList("name");
        Map<String, String> bookCategories = new LinkedHashMap<>();
        for (int i = 0; i <=bc.size()-1 ; i++) {
            bookCategories.put(jp.getString("id["+i+"]"), jp.getString("name["+i+"]"));
        }
        bookCategories.entrySet().forEach(System.out::println);
    }


    // Add new book to the library and assert through api if that's added
    // Book name: "This is my world"
    ///get_book_list_for_borrowing


    @Test
    public void testIfBookPosted() {

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .header("x-library-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjA5MCIsImZ1bGxfbmFtZSI6IlRlc3QgTGlicmFyaWFuIDY5IiwiZW1haWwiOiJsaWJyYXJpYW42OUBsaWJyYXJ5IiwidXNlcl9ncm91cF9pZCI6IjIifSwiaWF0IjoxNjA5ODIwMTQ3LCJleHAiOjE2MTI0MTIxNDd9.tnboSHrwWP3VjEvSsuBaUNx5zOu2cLvKQzOQLLxZX3M").
                when()
                        .get("/get_book_list_for_borrowing").
                then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        //.log().all()
                        .extract().response();

        JsonPath jp = response.jsonPath();
        List<String> bookList = jp.getList("name"); // this is here to get the size of list
        Map<String, String> libraryBooks = new LinkedHashMap<>();
        for (int i = 0; i <= bookList.size()-1; i++) {
            libraryBooks.put(jp.getString("id["+i+"]"), jp.getString("name["+i+"]"));
        }
        //libraryBooks.entrySet().stream().filter(eachSet-> eachSet.getValue().contains("Fantasy")).forEach(System.out::println);
        libraryBooks.entrySet().stream().forEach(eachSet-> System.out.println("id: "+eachSet.getKey()+" -----> "+"value: "+eachSet.getValue()));




    }
}

