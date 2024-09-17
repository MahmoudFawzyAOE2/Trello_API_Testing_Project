package TestSuite.CardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.not;

public class CreateAndUpdateCard extends Base {

    String cardId ;
    @Test(invocationCount = 5)
    @Description("Verify Card Creation & Updating when sending valid post & put requests")
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
                .when().post(URLs.cards)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("id", not(empty()))
                .extract().response();

        // to be used in the updateCard test case
        // note: this is an internal variable, not to be confused with Board.getCardID()
        cardId = re.path("id");
    }

    public void updateCard () throws InterruptedException {

        // small delay, so you can see the cards moving on the website
        // comment this command if you are focusing only on actual test results
        // TimeUnit.SECONDS.sleep(30);

        given().spec(request)
                .queryParam("idList", Board.getListID2())
                .queryParam("desc", faker.animal().name())
                .pathParam("id", cardId)
                .when().put(URLs.cards_id)
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
