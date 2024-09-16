package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetList extends Base {

    @Test()
    public void getList () {
        // end point customization
        String endPoint = URLs.lists+Board.getListID2();

        given().spec(request)
                .when().get(endPoint)
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
