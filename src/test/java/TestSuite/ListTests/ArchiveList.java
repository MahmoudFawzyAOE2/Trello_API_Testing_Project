package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ArchiveList extends Base {
    @Test()
    public void createAndArchiveList () {

        // end point customization
        String endPoint2 = URLs.lists+Board.getListID3()+"/closed/";

        given().spec(request)
                .queryParam("value", "true")
                .when().put(endPoint2)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .extract().response();
    }
}
