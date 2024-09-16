package TestSuite.CardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateAndUpdateCard extends Base {

    String cardId ;
    @Test(invocationCount = 5)
    public void createAndUpdateCard() throws InterruptedException {
        // here we run 2 test cases 5 times one after another
        createCard();
        updateCard();
    }

    public void createCard () throws InterruptedException {

        Response re = given().spec(request)
                .header("Content-Type", "application/json")
                .queryParam("idList", Board.getListID1())
                .queryParam("name", faker.commerce().material())
                //.queryParam("desc", faker.demographic().race())
                .when().post(URLs.cards)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .extract().response();

        // to be used in the updateCard test case
        cardId = re.path("id");
    }

    public void updateCard () throws InterruptedException {

        // small delay, so you can see the cards moving on the website
        // comment this command if you are focusing on actual test results
        // TimeUnit.SECONDS.sleep(30);

        // end point customization
        String endPoint = URLs.cards+cardId;

        given().spec(request)
                .queryParam("idList", Board.getListID2())
                .queryParam("desc", faker.animal().name())
                .when().put(endPoint)
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
