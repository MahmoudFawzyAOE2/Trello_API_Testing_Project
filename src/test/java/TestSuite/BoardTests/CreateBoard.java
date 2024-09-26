package TestSuite.BoardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateBoard extends Base {

    @Test()
    @Description("Verify creation of a new board")
    public void createBoard () {
        // Generate Board name using Faker
        String fakeBoardName = faker.name().firstName();
        System.out.println("fakeBoardName: "+ fakeBoardName);

        // add the name to the board data
        Board.setName(fakeBoardName);

        Response re = given().spec(request)
                .contentType(ContentType.JSON)
                .queryParam("name", fakeBoardName)
                .when().post(URLs.boards)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .extract().response();

        // storing Board ID
        Board.setId(re.path("id"));
    }

    @Test(dependsOnMethods = "createBoard")
    @Description("Verify that a newly created Board has no Cards")
    public void getCardsOnNewBoard() {

        given().spec(request)
                .pathParam("id", Board.getId())
                .when().get(URLs.boards_id_cards)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON);
    }
}

