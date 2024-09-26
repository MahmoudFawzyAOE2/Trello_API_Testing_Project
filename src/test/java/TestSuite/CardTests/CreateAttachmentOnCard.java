package TestSuite.CardTests;

import EndPoints.URLs;
import TestSuite.BaseTest.Base;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateAttachmentOnCard extends Base {

    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard",
                            "TestSuite.BoardTests.GetListsOnBoard.getListsOnBoard" ,
                            "TestSuite.CardTests.CreateAndUpdateCard.createAndUpdateCard",
                            "TestSuite.ListTests.GetCardsOnList.getCardsOnList"})
    @Description("Verify creating an attachment to a Card with valid post request")
    public void createAttachmentOnCard() {

        Response re = given().spec(request)
                .queryParam("name", faker.file().fileName())
                .multiPart("file", new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Cat.jpg"))
                .header("Content-Type", "multipart/form-data")
                .pathParam("id", Board.getCardID())
                .when().post(URLs.cards_id_attachments)
                .then().log().all()
                .assertThat().statusCode(200).extract().response();

        Board.setAttachID(re.path("id"));
    }

    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard",
            "TestSuite.BoardTests.GetListsOnBoard.getListsOnBoard" ,
            "TestSuite.CardTests.CreateAndUpdateCard.createAndUpdateCard",
            "TestSuite.ListTests.GetCardsOnList.getCardsOnList", "createAttachmentOnCard"})
    @Description("Verify adding an attachment as a valid cover to a Card with valid post request")
    public void createAttachmentMP4Cover() {
        // end point customization
        String endPoint = URLs.cards+Board.getCardID()+"/attachments";

        given().spec(request)
                .queryParam("name", faker.file().fileName())
                .queryParam("setCover", true)
                .multiPart("file", new File(System.getProperty("user.dir") +"\\src\\main\\resources\\UEL.mp4"))
                .header("Content-Type", "multipart/form-data")
                .pathParam("id", Board.getCardID())
                .when().post(URLs.cards_id_attachments)
                .then().log().all()
                .assertThat().statusCode(400);
    }

    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard",
            "TestSuite.BoardTests.GetListsOnBoard.getListsOnBoard" ,
            "TestSuite.CardTests.CreateAndUpdateCard.createAndUpdateCard",
            "TestSuite.ListTests.GetCardsOnList.getCardsOnList", "createAttachmentOnCard"})
    @Description("Verify adding an attachment as a valid cover to a Card with valid post request")
    public void createAttachmentPDFCover() {

        given().spec(request)
                .queryParam("name", faker.file().fileName())
                .queryParam("setCover", true)
                .multiPart("file", new File(System.getProperty("user.dir") +"\\src\\main\\resources\\RA.pdf"))
                .header("Content-Type", "multipart/form-data")
                .pathParam("id", Board.getCardID())
                .when().post(URLs.cards_id_attachments)
                .then().log().all()
                .assertThat().statusCode(400);
    }

    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard",
            "TestSuite.BoardTests.GetListsOnBoard.getListsOnBoard" ,
            "TestSuite.CardTests.CreateAndUpdateCard.createAndUpdateCard",
            "TestSuite.ListTests.GetCardsOnList.getCardsOnList", "createAttachmentOnCard"})
    @Description("Verify adding an attachment as a valid cover to a Card with valid post request")
    public void createAttachmentJPGCover() {

        given().spec(request)
                .queryParam("name", faker.file().fileName())
                .queryParam("setCover", true)
                .multiPart("file", new File(System.getProperty("user.dir") +"\\src\\main\\resources\\INFP.jpg"))
                .header("Content-Type", "multipart/form-data")
                .pathParam("id", Board.getCardID())
                .when().post(URLs.cards_id_attachments)
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = {"TestSuite.BoardTests.CreateBoard.createBoard",
            "TestSuite.BoardTests.GetListsOnBoard.getListsOnBoard" ,
            "TestSuite.CardTests.CreateAndUpdateCard.createAndUpdateCard",
            "TestSuite.ListTests.GetCardsOnList.getCardsOnList", "createAttachmentOnCard"})
    @Description("Verify adding an attachment as a valid cover to a Card with Cover")
    public void createAttachmentGIFCover() {

        given().spec(request)
                .queryParam("name", faker.file().fileName())
                .queryParam("setCover", true)
                .multiPart("file", new File(System.getProperty("user.dir") +"\\src\\main\\resources\\kawaii.gif"))
                .header("Content-Type", "multipart/form-data")
                .pathParam("id", Board.getCardID())
                .when().post(URLs.cards_id_attachments)
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
