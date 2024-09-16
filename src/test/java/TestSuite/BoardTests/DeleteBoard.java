package TestSuite.BoardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import UserData.AuthCredentials;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBoard extends Base {

    @Test()
    public void deleteBoard () {
        // end point customization
        String endPoint = URLs.boards+Board.getId();

        given().spec(request)
                .when().delete(endPoint)
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test()
    public void getBoardAfterDelete () {   // verify that the deleted Board cannot be retrieved
        // end point customization
        String endPoint = URLs.boards+Board.getId();

        given().spec(request)
                .queryParams(AuthCredentials.getAuthParams())
                .when().get(endPoint)
                .then().log().all()
                .assertThat().statusCode(404);
    }
}
