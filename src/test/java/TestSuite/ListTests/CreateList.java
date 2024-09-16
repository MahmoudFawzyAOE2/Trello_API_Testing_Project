package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateList extends Base {
    @Test()
    public void createAndArchiveList() {

        Response re = given().spec(request)
                .contentType(ContentType.JSON)
                .queryParam("name", faker.cat().name())
                .queryParam("idBoard", Board.getId())
                .when().post(URLs.lists)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .extract().response();
    }
}
