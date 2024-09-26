package TestSuite.BoardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetCardsOnBoard extends Base {
    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard"})
    @Description("Verify retrieving an array of Cards in Board when sending valid get request")
    public void getCardsOnBoard() {

        given().spec(request)
                .pathParam("id", Board.getId())
                .when().get(URLs.boards_id_cards)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON);
    }
}
