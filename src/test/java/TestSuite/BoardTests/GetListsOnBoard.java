package TestSuite.BoardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetListsOnBoard extends Base {

    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard"})
    @Description("Verify retrieving an array of Lists in Board when sending valid get request")
    public void getListsOnBoard () {

        Response re = given().spec(request)
                .pathParam("id", Board.getId())
                .when().get(URLs.boards_id_lists)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("id.size()", equalTo(3)).extract().response();

        Board.setListID1(re.path("[0].id"));
        Board.setListID2(re.path("[1].id"));
        Board.setListID3(re.path("[2].id"));
    }
}
