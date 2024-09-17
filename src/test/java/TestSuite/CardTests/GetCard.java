package TestSuite.CardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.not;

public class GetCard extends Base {

    @Test()
    @Description("Verify retrieving Card info when sending valid get request")
    public void getCard () {

        given().spec(request)
                .pathParam("id", Board.getCardID())
                .when().get(URLs.cards_id)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", not(empty()));
    }
}
