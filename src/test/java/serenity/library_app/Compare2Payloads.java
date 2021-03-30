package serenity.library_app;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import serenity.utility.SpartanUtil;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;
import java.util.Map;

public class Compare2Payloads {

    @Test
    public void compare_payloads() throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject object1 = SpartanUtil.getJsonObject("src/test/java/payloads/payload1.json");
        JSONObject object2 = SpartanUtil.getJsonObject("src/test/java/payloads/payload2.json");

        object1.toJSONString();
        object2.toJSONString();

        Assertions.assertEquals(object1,object2);

    }
}
