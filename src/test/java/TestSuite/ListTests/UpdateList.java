package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateList extends Base {
    @Test()
    public void updateList () {
        // end point customization
        String endPoint = URLs.lists+Board.getListID1();

        given().spec(request)
                .queryParams("name", faker.job().field())
                .when().put(endPoint)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON);

        // end point customization
        String endPoint2 = URLs.lists+Board.getListID2();

        given().spec(request)
                .contentType(ContentType.JSON)
                .queryParam("name", faker.job().field())
                .when().put(endPoint2)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON);

    }
}
