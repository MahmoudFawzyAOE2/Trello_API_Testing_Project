package TestSuite.CardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetCard extends Base {

    @Test()
    public void getCard () {
        // end point customization
        String endPoint = URLs.cards+Board.getCardID();

        given().spec(request)
                .when().get(endPoint)
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
