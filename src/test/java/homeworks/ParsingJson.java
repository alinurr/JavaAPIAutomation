package homeworks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

public class ParsingJson {
    @Test
    public void testJsonParse(){
        JsonPath response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        String name = response.get("messages[1].message");
        System.out.println(name);
    }
}
