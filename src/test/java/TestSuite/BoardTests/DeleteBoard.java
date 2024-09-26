package TestSuite.BoardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBoard extends Base {

    @Test(dependsOnMethods = "TestSuite.BoardTests.CreateBoard.createBoard")
    @Description("Verify Board deletion when sending valid delete request")
    public void deleteBoard () {

        System.out.println("Request: " + request);
        Response re = given().spec(request)
                .pathParam("id", Board.getId())
                .when().delete(URLs.boards_id)
                .then().log().all()
                .assertThat().statusCode(200).extract().response();
    }

    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard", "deleteBoard"})
    @Description("Verify deleted Board cannot be retrieved with get request")
    public void getBoardAfterDelete () {   // verify that the deleted Board cannot be retrieved

        given().spec(request)
                .pathParam("id", Board.getId())
                .when().get(URLs.boards_id)
                .then().log().all()
                .assertThat().statusCode(404);
    }
}
