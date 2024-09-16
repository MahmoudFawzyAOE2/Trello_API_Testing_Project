package TestSuite.CardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteCard extends Base {
    @Test()
    public void deleteCard () {
        // end point customization
        String endPoint = URLs.cards+Board.getCardID();

        given().spec(request)
                .when().delete(endPoint)
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test()
    public void getCardAfterDelete () {   // verify that the deleted card cannot be retrieved
        // end point customization
        String endPoint = URLs.cards+Board.getCardID();

        given().spec(request)
                .when().get(endPoint)
                .then().log().all()
                .assertThat().statusCode(404);
    }
}
