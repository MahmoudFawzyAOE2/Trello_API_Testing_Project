package TestSuite.BoardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import UserData.AuthCredentials;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBoard extends Base {

    @Test()
    public void getBoard () {
        // end point customization
        String endPoint = URLs.boards+Board.getId();

        given().spec(request)
                .queryParams(AuthCredentials.getAuthParams())
                .when().get(endPoint)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON);
    }
}
