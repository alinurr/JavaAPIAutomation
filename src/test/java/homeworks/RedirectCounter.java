package homeworks;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


public class RedirectCounter {
    @Test
    public void testRedirectCounter(){

        // Исходный URL, с которого начинается цикл редиректов
        String initialUrl = "https://playground.learnqa.ru/api/long_redirect";

        // Текущий URL для отправки запроса
        String currentUrl = initialUrl;

        // Счетчик редиректов
        int redirectCount = 0;

        while (true) {
            // Выполняем GET-запрос и не следуем автоматическим редиректам
            Response response = RestAssured
                    .given()
                    .redirects().follow(false) // Отключаем автоматический редирект
                    .when()
                    .get(currentUrl);

            int statusCode = response.getStatusCode();

            // Если получен код 200, завершаем цикл
            if (statusCode == 200) {
                System.out.println("Код 200 получен. Редиректы завершены.");
                break;
            }

            // Получаем URL из заголовка "Location"
            String redirectUrl = response.getHeader("Location");

            // Если заголовок присутствует, продолжаем редирект
            if (redirectUrl != null) {
                currentUrl = redirectUrl;
                redirectCount++;
                System.out.println("Редирект #" + redirectCount + " на: " + currentUrl);
            } else {
                // Если заголовка с редиректом нет, завершаем выполнение
                System.out.println("Заголовок " + "Location" + " отсутствует. Завершение.");
                break;
            }
        }

        // Вывод общего количества редиректов
        System.out.println("Общее количество редиректов: " + redirectCount);
    }
}
