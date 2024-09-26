package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.not;

public class GetList extends Base {

    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard",
            "TestSuite.BoardTests.GetListsOnBoard.getListsOnBoard"})
    @Description("Verify retrieving List info when sending valid get request")
    public void getList () {

        given().spec(request)
                .pathParam("id", Board.getListID2())
                .when().get(URLs.lists_id)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", not(empty()));
    }
}
