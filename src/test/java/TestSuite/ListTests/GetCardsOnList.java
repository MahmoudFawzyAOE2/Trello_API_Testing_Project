package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class GetCardsOnList extends Base {
    @Test()
    public void getCardsOnList () {
        // end point customization
        String endPoint = URLs.lists+Board.getListID2()+"/cards";

        Response re = given().spec(request)
                .when().get(endPoint)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", hasSize(5))
                .extract().response();

        Board.setCardID(re.path("[4].id"));
    }
}
