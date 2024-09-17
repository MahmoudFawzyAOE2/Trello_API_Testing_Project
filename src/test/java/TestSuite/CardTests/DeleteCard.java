package TestSuite.CardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteCard extends Base {
    @Test()
    @Description("Verify Card deletion when sending valid delete request")
    public void deleteCard () {

        given().spec(request)
                .pathParam("id", Board.getCardID())
                .when().delete(URLs.cards_id)
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test()
    @Description("Verify deleted Card cannot be retrieved with get request")
    public void getCardAfterDelete () {   // verify that the deleted card cannot be retrieved

        given().spec(request)
                .pathParam("id", Board.getCardID())
                .when().get(URLs.cards_id)
                .then().log().all()
                .assertThat().statusCode(404);
    }
}
