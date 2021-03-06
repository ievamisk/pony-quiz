package mylittlepony.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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

public class PonyResourceTest {

    public static Map map = new HashMap();
    public static Map map2 = new HashMap();
    public static Map questions = new HashMap();

    @Before
    public void postdata(){


        map.put("name", "Little Starr");
        map.put("description", "You dance at the rodeo and would  play sports with friends all day!");
        map.put("image", "https://pre00.deviantart.net/3d51/th/pre/f/2011/272/e/7/star_dash_request_by_rildraw-d4banm0.png");


        map2.put("_id", "10");
        map2.put("name", "Little Starr");
        map2.put("description", "You dance at the rodeo and would  play sports with friends all day!");
        map2.put("image", "https://pre00.deviantart.net/3d51/th/pre/f/2011/272/e/7/star_dash_request_by_rildraw-d4banm0.png");
        map2.put("questions", questions);
    }

    @Test
    public void whenPostPonyShouldSuccess() {

        given().
                body(map)
                .when()
                .contentType(ContentType.JSON)
                .post("http://localhost:8080/api/insertPony");
    }

    @Test
    public void whenUpdatePonyShouldSuccess() {

        given().
                body(map2)
                .when()
                .contentType(ContentType.JSON)
                .post("http://localhost:8080/api/updatePony");
    }

    @Test
    public void whenRequestGet_thenOK(){
        when().request("GET", "/api/fullPonyInfo/1").then().statusCode(200);
    }

    @Test
    public void whenMeasureResponseTime_thenOK() {

        Response response = RestAssured.get("/api/fullPonyInfo/1");
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);

        Assert.assertEquals(timeInS, timeInMS/1000);
    }

    @Test
    public void whenValidateResponseTime_thenSuccess() {
        when().get("api/fullPonyInfo/").then().time(lessThan(5000L));
    }

    @Test
    public void whenLogRequest_thenOK() {
        given().log().all()
                .when().get("api/fullPonyInfo/1")
                .then().statusCode(200);
    }

    @Test
    public void whenLogResponseIfErrorOccurred_thenSuccess() {

        when().get("api/fullPonyInfo/1")
                .then().log().ifError();
        when().get("api/fullPonyInfo/1")
                .then().log().ifStatusCodeIsEqualTo(500);
        when().get("api/fullPonyInfo/1")
                .then().log().ifStatusCodeMatches(greaterThan(200));
    }

    @Test
    public void whenLogOnlyIfValidationFailed_thenSuccess() {
        when().get("api/fullPonyInfo/1")
                .then().log().ifValidationFails().statusCode(200);

        given().log().ifValidationFails()
                .when().get("api/fullPonyInfo/1")
                .then().statusCode(200);
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
        get("/api/fullPonyInfo/1").then().statusCode(200).assertThat()
                .body("name",  equalTo("Night Star"));
    }

    @Test
    public void deleteTest () {

        given()
                .when ()
                .contentType (ContentType.JSON)
                .delete ("/api/ponies/7");
    }


}