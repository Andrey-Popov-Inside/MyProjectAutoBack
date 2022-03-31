package HomeWorkLesson3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainGetTest {

    private final String apiKey = "2975e125ca7f480ead94ac3cd699234d";

    @Test
    void getTestOne(){
        given()
                .queryParam("apiKey", apiKey)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getTestTwo(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "burger")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("number"),equalTo(10));
    }

    @Test
    void getTestThree(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("cuisine","italian")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("number"),equalTo(10));
        assertThat(response.get("totalResults"),equalTo(263));
    }

    @Test
    void getTestFour(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("excludeCuisine","greek")
                .queryParam("query","burger")
                .queryParam("maxCalories","800")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("number"),equalTo(10));
        assertThat(response.get("totalResults"),equalTo(8));
    }

    @Test
    void getTestFive(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("diet","vegetarian")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("number"),equalTo(10));
        assertThat(response.get("totalResults"),equalTo(2219));
    }

    @Test
    void getTestSix(){
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .queryParam("includeIngredients","tomato,cheese")
                .queryParam("maxCalories", "800")
                .queryParam("minCalories","300")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("number"),equalTo(10));
        assertThat(response.get("totalResults"),equalTo(169));
    }
}
