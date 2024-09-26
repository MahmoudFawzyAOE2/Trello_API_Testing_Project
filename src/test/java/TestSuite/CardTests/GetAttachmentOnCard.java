package TestSuite.CardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import UserData.AuthCredentials;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.not;

public class GetAttachmentOnCard extends Base {

    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard",
            "TestSuite.BoardTests.GetListsOnBoard.getListsOnBoard" ,
            "TestSuite.CardTests.CreateAndUpdateCard.createAndUpdateCard",
            "TestSuite.ListTests.GetCardsOnList.getCardsOnList",
            "TestSuite.CardTests.CreateAttachmentOnCard.createAttachmentOnCard"})
    @Description("Verify retrieving Board info when sending valid get request")
    public void getAttachment () {

        given().spec(request)
                .queryParams(AuthCredentials.getAuthParams())
                .pathParam("id", Board.getCardID())
                .pathParam("idAttachment", Board.getAttachID())
                .when().get(URLs.cards_id_attach_idAttach)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .assertThat().body("id", not(empty()));
    }
}
