package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class GetCardsOnList extends Base {
    @Test()
    @Description("Verify retrieving an array of cards in List when sending valid get request")
    public void getCardsOnList () {

        Response re = given().spec(request)
                .pathParam("id", Board.getListID2())
                .when().get(URLs.lists_id_cards)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", hasSize(5))
                .extract().response();

        Board.setCardID(re.path("[4].id"));
    }
}
