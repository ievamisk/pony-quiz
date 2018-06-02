package mylittlepony.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

public class BasicQuestionResourceTest {

    public static Map map = new HashMap();
    @Before
    public void postdata(){
        map.put("question", "Fave food");
        map.put("image", "http://i0.kym-cdn.com/photos/images/original/000/277/529/7aa.png");
    }

    @Test
    public void whenPostQuestionShouldSuccess() {

        given().
                body(map)
                .when()
                .contentType(ContentType.JSON)
                .post("http://localhost:8080/api/question");
    }

    @Test
    public void whenRequestGet_thenOK(){
        when().request("GET", "/api/questions").then().statusCode(200);
    }

    @Test
    public void whenMeasureResponseTime_thenOK() {

        Response response = RestAssured.get("/api/questions");
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);

        Assert.assertEquals(timeInS, timeInMS/1000);
    }

    @Test
    public void whenValidateResponseTime_thenSuccess() {
        when().get("api/questions").then().time(lessThan(5000L));
    }

    @Test
    public void whenLogRequest_thenOK() {
        given().log().all()
                .when().get("api/questions")
                .then().statusCode(200);
    }

    @Test
    public void whenLogResponseIfErrorOccurred_thenSuccess() {

        when().get("api/questions")
                .then().log().ifError();
        when().get("api/questions")
                .then().log().ifStatusCodeIsEqualTo(500);
        when().get("api/questions")
                .then().log().ifStatusCodeMatches(greaterThan(200));
    }

    @Test
    public void whenLogOnlyIfValidationFailed_thenSuccess() {
        when().get("api/questions")
                .then().log().ifValidationFails().statusCode(200);

        given().log().ifValidationFails()
                .when().get("api/questions")
                .then().statusCode(200);
    }

    @Test
    public void deleteTest () {
        given()
                .when ()
                .contentType (ContentType.JSON)
                .delete ("/api/questions/10");
    }
}