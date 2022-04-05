package HomeWorkLesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class MainGetTestSpec {

    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;
    private final String apiKey = "2975e125ca7f480ead94ac3cd699234d";

    @BeforeEach
    void beforeTest(){
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(10000L))
                .build();

        RestAssured.responseSpecification = responseSpecification;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void getTestOne(){
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getTestTwo(){
        given().spec(requestSpecification)
                .when()
                .queryParam("query", "burger")
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getTestThree(){
        given().spec(requestSpecification)
                .when()
                .queryParam("cuisine","italian")
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getTestFour(){
        given().spec(requestSpecification)
                .when()
                .queryParam("excludeCuisine","greek")
                .queryParam("query","burger")
                .queryParam("maxCalories","800")
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getTestFive(){
        given().spec(requestSpecification)
                .when()
                .queryParam("diet","vegetarian")
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getTestSix(){
        given().spec(requestSpecification)
                .when()
                .queryParam("includeIngredients","tomato,cheese")
                .queryParam("maxCalories", "800")
                .queryParam("minCalories","300")
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
}
