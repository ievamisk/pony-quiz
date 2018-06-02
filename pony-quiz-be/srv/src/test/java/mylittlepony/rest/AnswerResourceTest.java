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

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class AnswerResourceTest {


    public static Map map = new HashMap();
    @Before
    public void postdata(){
        map.put("_id", "56");
        map.put("ponyId", "5");
        map.put("questionId", "8");
        map.put("answer", "Test");
    }

    @Test
    public void whenRequestGet_thenOK(){
        when().request("GET", "/api/answers").then().statusCode(200);
    }

    @Test
    public void whenMeasureResponseTime_thenOK() {

        Response response = RestAssured.get("/api/answers");
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);

        Assert.assertEquals(timeInS, timeInMS/1000);
    }

    @Test
    public void whenValidateResponseTime_thenSuccess() {
        when().get("/api/answers").then().time(lessThan(5000L));
    }

    @Test
    public void whenLogRequest_thenOK() {
        given().log().all()
                .when().get("/api/answers")
                .then().statusCode(200);
    }

    @Test
    public void whenLogResponseIfErrorOccurred_thenSuccess() {

        when().get("/api/answers")
                .then().log().ifError();
        when().get("/api/answers")
                .then().log().ifStatusCodeIsEqualTo(500);
        when().get("/api/answers")
                .then().log().ifStatusCodeMatches(greaterThan(200));
    }

    @Test
    public void whenLogOnlyIfValidationFailed_thenSuccess() {
        when().get("/api/answers")
                .then().log().ifValidationFails().statusCode(200);

        given().log().ifValidationFails()
                .when().get("/api/answers")
                .then().statusCode(200);
    }

    @Test
    public void whenPostAnswerShouldSuccess() {

        given().
               body(map)
               .when()
               .contentType(ContentType.JSON)
               .post("http://localhost:8080/api/answers");
    }

}