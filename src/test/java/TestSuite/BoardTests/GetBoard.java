package TestSuite.BoardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import UserData.AuthCredentials;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.not;

public class GetBoard extends Base {

    @Test()
    @Description("Verify retrieving Board info when sending valid get request")
    public void getBoard () {

        given().spec(request)
                .pathParam("id", Board.getId())
                .when().get(URLs.boards_id)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("id", not(empty()));
    }
}
