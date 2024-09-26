package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ArchiveList extends Base {
    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard",
            "TestSuite.BoardTests.GetListsOnBoard.getListsOnBoard"})
    @Description("Verify Archiving Card when sending valid put request")
    public void archiveList () {

        given().spec(request)
                .queryParam("value", "true")
                .pathParam("id", Board.getListID3())
                .when().put(URLs.lists_id_closed)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("closed", equalTo(true));
    }

    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard",
            "TestSuite.BoardTests.GetListsOnBoard.getListsOnBoard", "archiveList"})
    @Description("Verify un-archiving List when sending valid put request")
    public void unarchiveList () {

        given().spec(request)
                .queryParam("value", "false")
                .pathParam("id", Board.getListID3())
                .when().put(URLs.lists_id_closed)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("closed", equalTo(false));
    }
}
