package TestSuite.ListTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.not;

public class CreateList extends Base {
    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard"})
    @Description("Verify creating List when sending valid post request")
    public void createList() {

        given().spec(request)
                .contentType(ContentType.JSON)
                .queryParam("name", faker.cat().name())
                .queryParam("idBoard", Board.getId())
                .when().post(URLs.lists)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("id", not(empty()));
    }
}
