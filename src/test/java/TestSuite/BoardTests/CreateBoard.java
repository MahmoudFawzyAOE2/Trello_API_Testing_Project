package TestSuite.BoardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateBoard extends Base {

    @Test()
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

        Board.setId(re.path("id"));
        System.out.println(Board.getId());

        Board.setUrl(re.path("url"));
        System.out.println(Board.getUrl());
    }
}

