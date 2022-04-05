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
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainPostTestSpec {

    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;
    private final String apiKey = "2975e125ca7f480ead94ac3cd699234d";

    @BeforeEach
    void beforeTest() {
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
    void postTestOne() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","sushi")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Japanese"));
    }
    @Test
    void postTestTwo() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","burger")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("American"));
        assertThat(response.getConfidence(), equalTo(0.85));
    }
    @Test
    void postTestThree() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","French fries")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("American"));
        assertThat(response.getConfidence(), equalTo(0.85));
    }
    @Test
    void postTestFour() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","Spaghetti Carbonara")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
        assertThat(response.getConfidence(), equalTo(0.95));
    }
    @Test
    void postTestFive() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","Chickpea Bruschetta")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
        assertThat(response.getConfidence(), equalTo(0.95));
    }
    @Test
    void postTestSix() {
        given().spec(requestSpecification)
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }
}