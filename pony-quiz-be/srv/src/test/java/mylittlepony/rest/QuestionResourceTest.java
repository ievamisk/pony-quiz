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

public class QuestionResourceTest {

    public static Map map = new HashMap();
    public static Map map2 = new HashMap();

    @Before
    public void postdata() {
        map.put("question", "You love");
        map.put("image", "http://i0.kym-cdn.com/photos/images/original/000/277/529/7aa.png");
        map.put("answerAndPony", "1");

        map2.put("_id", "10");
        map2.put("question", "You loveUpdate");
        map2.put("image", "http://i0.kym-cdn.com/photos/images/original/000/277/529/7aa.png");
        map2.put("answerAndPony", "1");

    }

    @Test
    public void whenRequestGet_thenOK(){
        when().request("GET", "/api/ponyForQuestions").then().statusCode(200);
    }

    @Test
    public void whenMeasureResponseTime_thenOK() {

        Response response = RestAssured.get("/api/ponyForQuestions");
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);

        Assert.assertEquals(timeInS, timeInMS/1000);
    }

    @Test
    public void whenValidateResponseTime_thenSuccess() {
        when().get("api/ponyForQuestions").then().time(lessThan(5000L));
    }

    @Test
    public void whenLogRequest_thenOK() {
        given().log().all()
                .when().get("api/ponyForQuestions")
                .then().statusCode(200);
    }

    @Test
    public void whenLogResponseIfErrorOccurred_thenSuccess() {

        when().get("api/ponyForQuestions")
                .then().log().ifError();
        when().get("api/ponyForQuestions")
                .then().log().ifStatusCodeIsEqualTo(500);
        when().get("api/ponyForQuestions")
                .then().log().ifStatusCodeMatches(greaterThan(200));
    }

    @Test
    public void whenLogOnlyIfValidationFailed_thenSuccess() {
        when().get("api/ponyForQuestions")
                .then().log().ifValidationFails().statusCode(200);

        given().log().ifValidationFails()
                .when().get("api/ponyForQuestions")
                .then().statusCode(200);
    }

    @Test
    public void whenRequestGet_thenOKOneQuestion(){
        when().request("GET", "/api/questionInfo/1").then().statusCode(200);
    }

    @Test
    public void whenMeasureResponseTime_thenOKOneQuestion() {

        Response response = RestAssured.get("/api/questionInfo/1");
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);

        Assert.assertEquals(timeInS, timeInMS/1000);
    }

    @Test
    public void whenValidateResponseTime_thenSuccessOneQuestion() {
        when().get("api/questionInfo/1").then().time(lessThan(5000L));
    }

    @Test
    public void whenLogRequest_thenOKOneQuestion() {
        given().log().all()
                .when().get("api/questionInfo/1")
                .then().statusCode(200);
    }

    @Test
    public void whenLogResponseIfErrorOccurred_thenSuccessOneQuestion() {

        when().get("api/questionInfo/1")
                .then().log().ifError();
        when().get("api/questionInfo/1")
                .then().log().ifStatusCodeIsEqualTo(500);
        when().get("api/questionInfo/1")
                .then().log().ifStatusCodeMatches(greaterThan(200));
    }

    @Test
    public void whenLogOnlyIfValidationFailed_thenSuccessOneQuestion() {
        when().get("api/questionInfo/1")
                .then().log().ifValidationFails().statusCode(200);

        given().log().ifValidationFails()
                .when().get("api/questionInfo/1")
                .then().statusCode(200);
    }

    @Test
    public void whenPostQuestionShouldSuccess() {

        given().
                body(map)
                .when()
                .contentType(ContentType.JSON)
                .post("http://localhost:8080/api/addQuestion");
    }

    @Test
    public void whenUpdateQuestionShouldSuccess() {

        given().
                body(map2)
                .when()
                .contentType(ContentType.JSON)
                .post("http://localhost:8080/api/updateQuestion");
    }

    @Test
    public void deleteTest () {
        given()
                .when ()
                .contentType (ContentType.JSON)
                .delete ("/api/deleteQuestions/10");
    }
}