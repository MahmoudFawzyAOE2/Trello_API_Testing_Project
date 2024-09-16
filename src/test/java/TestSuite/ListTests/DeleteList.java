package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteList extends Base {

    @Test()
    public void deleteList () {   // verify that the delete List request in not implemented
        // end point customization
        String endPoint = URLs.lists+Board.getListID3();

        given().spec(request)
                .when().delete(endPoint)
                .then().log().all()
                .assertThat().statusCode(501);
    }
}
