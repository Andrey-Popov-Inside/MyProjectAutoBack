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

public class EndPointShopingListTest {

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
    void getTestShopList(){
        given().spec(requestSpecification)
                .queryParam("hash", "1ebb1d542590ffe6823ac1f5cd42b837ffbc6dda")
                .when()
                .get("https://api.spoonacular.com/mealplanner/your-users-name56/shopping-list?hash=1ebb1d542590ffe6823ac1f5cd42b837ffbc6dda")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void postTestShopList() {
        given().spec(requestSpecification)
                .queryParam("hash", "1ebb1d542590ffe6823ac1f5cd42b837ffbc6dda")
                .body("{\n"
                        + " \"item\": 1 package baking powder,\n"
                        + " \"aisle\": Baking,\n"
                        + " \"parse\": true,\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/your-users-name56/shopping-list/items?hash=1ebb1d542590ffe6823ac1f5cd42b837ffbc6dda")
                .then()
                .spec(responseSpecification);
    }
}
