package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBoardOfList extends Base {

    @Test
    @Description("Verify retrieving the Board which the List is on when sending valid get request")
    public void getBoardOfList () {

        given().spec(request)
                .pathParam("id", Board.getListID3())
                .when().get(URLs.lists_id_board)
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
