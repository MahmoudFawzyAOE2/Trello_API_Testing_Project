package TestSuites;

import EndPoints.URLs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
/*
public class ResetTestSuite {

    @Test(priority = 1)
    public void createBoard () {

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

 */
