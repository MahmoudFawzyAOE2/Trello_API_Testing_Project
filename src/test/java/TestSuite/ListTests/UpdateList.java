package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateList extends Base {
    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard",
            "TestSuite.BoardTests.GetListsOnBoard.getListsOnBoard"})
    @Description("Verify Updating List info when sending valid put request")
    public void updateList () {

        given().spec(request)
                .queryParams("name", faker.job().field())
                .pathParam("id", Board.getListID1())
                .when().put(URLs.lists_id)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON);

        given().spec(request)
                .contentType(ContentType.JSON)
                .queryParam("name", faker.job().field())
                .pathParam("id", Board.getListID2())
                .when().put(URLs.lists_id)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON);

    }
}
