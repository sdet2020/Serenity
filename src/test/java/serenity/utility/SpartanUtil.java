package serenity.utility;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {

    public static Map<String,Object> getRandomSpartanRequestPayload(){
        Faker faker = new Faker() ;
        Map<String,Object> payLoadMap = new LinkedHashMap<>();
        payLoadMap.put("name" , faker.name().firstName() ) ;
        payLoadMap.put("gender" , faker.demographic().sex() ) ;
        payLoadMap.put("phone" , faker.number().numberBetween(2000000000L, 9999999999L)) ;
        return payLoadMap ;
    }

    public static JSONObject getJsonObject(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(filePath);
        Object object = jsonParser.parse(fileReader);
        JSONObject payload = (JSONObject) object;
        return payload;

    }

}
