package homeworks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.lang.Thread;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Token {
    @Test
    public void testToken() throws InterruptedException {
        JsonPath response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String token = response.getString("token");

        JsonPath responseBefore = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job/?token=" + token)
                .jsonPath();

        assertEquals("Job is NOT ready", responseBefore.get("status"), "There is no field 'status'");

        Thread.sleep(25000);

        JsonPath responseAfter = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job/?token=" + token)
                .jsonPath();

        assertEquals("Job is ready", responseAfter.getString("status"), "There is no field 'status'");
        assertTrue(responseAfter.get("result") != null, "There is no field 'result'");
    }
}
