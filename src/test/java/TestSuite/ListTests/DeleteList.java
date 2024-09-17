package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteList extends Base {

    @Test()
    @Description("verify that the delete List request in not implemented")
    public void deleteList () {

        given().spec(request)
                .pathParam("id", Board.getListID3())
                .when().delete(URLs.lists_id)
                .then().log().all()
                .assertThat().statusCode(501);
    }
}
