package TestSuite.CardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import UserData.AuthCredentials;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteAttachmentOnCard extends Base {
    @Test()
    @Description("Verify attachment deletion when sending valid delete request")
    public void deleteAttachment () {

        given().spec(request)
                .pathParam("id", Board.getCardID())
                .pathParam("idAttachment", Board.getAttachID())
                .when().delete(URLs.cards_id_attach_idAttach)
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test()
    @Description("Verify deleted attachment cannot be retrieved with get request")
    public void getAttachmentAfterDelete () {   // verify that the deleted Board cannot be retrieved

        given().spec(request)
                .queryParams(AuthCredentials.getAuthParams())
                .pathParam("id", Board.getCardID())
                .pathParam("idAttachment", Board.getAttachID())
                .when().get(URLs.cards_id_attach_idAttach)
                .then().log().all()
                .assertThat().statusCode(404);
    }
}
