package HomeWorkLesson3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainPostTest {

    private final String apiKey = "2975e125ca7f480ead94ac3cd699234d";

    @Test
    void postTestOne() {
        given()
                .queryParam("apiKey", apiKey)
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void postTestTwo() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .formParam("title", "sushi")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), equalTo("Japanese"));
    }

    @Test
    void postTestThree() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .formParam("title", "burger")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), equalTo("American"));
        assertThat(response.get("confidence"), equalTo(0.85F));
    }

    @Test
    void postTestFour() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .formParam("title", "French fries")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), equalTo("American"));
        assertThat(response.get("confidence"), equalTo(0.85F));
    }

    @Test
    void postTestFive() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .formParam("title", "Spaghetti Carbonara")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), equalTo("Mediterranean"));
        assertThat(response.get("confidence"), equalTo(0.95F));
    }

    @Test
    void postTestSix() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .formParam("title", "Chickpea Bruschetta")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), equalTo("Mediterranean"));
        assertThat(response.get("confidence"), equalTo(0.95F));
    }
}
