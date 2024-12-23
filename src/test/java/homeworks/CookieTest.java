package homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CookieTest {
    @Test
    public void testGetCookie(){
        Response response = RestAssured
                .post("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        Map<String, String> responseCookies = response.getCookies();
        System.out.println(responseCookies);
        assertTrue(responseCookies.containsKey("HomeWork") && responseCookies.containsValue("hw_value"),  "There is no cookie");
    }
}
