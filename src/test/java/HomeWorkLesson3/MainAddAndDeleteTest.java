package HomeWorkLesson3;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class MainAddAndDeleteTest {

    private final String apiKey = "2975e125ca7f480ead94ac3cd699234d";

    @Test
    void addAndDeleteFromMealPlan() {
        String id = given()
                .queryParam("hash", "84514555d2ad3f1c851cf865e8835ba2ec7310a7")
                .queryParam("apiKey", apiKey)
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/your-users-name33/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
        given()
                .queryParam("hash", "84514555d2ad3f1c851cf865e8835ba2ec7310a7")
                .queryParam("apiKey", apiKey)
                .delete("https://api.spoonacular.com/mealplanner/your-users-name33/items/" + id)
                .then()
                .statusCode(200);
    }
}
