package TestSuites;

// local classes import
import EndPoints.URLs;
import UserData.AuthCredentials;
import PojoClasses.BoardPojo;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

// Static Imports
import static io.restassured.RestAssured.*;

public class TestSuite {

    private static BoardPojo Board = new BoardPojo() ;
    Faker faker = new Faker();

    @Test(priority = 1)
    public void createBoard () {

        // Generate Board name using Faker
        String fakeBoardName = faker.name().firstName();
        System.out.println("fakeBoardName: "+ fakeBoardName);

        // add the name to the board data
        Board.setName(fakeBoardName);

        Response re = given().baseUri(URLs.BaseURL)
                .contentType(ContentType.JSON)
                .queryParam("name", fakeBoardName)
                .queryParams(AuthCredentials.getAuthParams())
                .when().post(URLs.boards)
                .then().log().all()
                .assertThat().statusCode(200).extract().response();

        Board.setId(re.path("id"));
        System.out.println(Board.getId());

        Board.setUrl(re.path("url"));
        System.out.println(Board.getUrl());
    }

    @Test(priority = 2)
    public void getBoard () {
        // end point customization
        String endPoint = URLs.boards+Board.getId();

        given().baseUri(URLs.BaseURL)
                .header("Accept", "application/json")
                .queryParams(AuthCredentials.getAuthParams())
                .when().get(endPoint)
                .then().log().all()
                .assertThat().statusCode(200);
    }


    @Test(priority = 3)
    public void getListsOnBoard () {
        // end point customization
        String endPoint = URLs.boards+Board.getId()+"/lists";

        Response re = given().baseUri(URLs.BaseURL)
                .header("Accept", "application/json")
                .queryParams(AuthCredentials.getAuthParams())
                .when().get(endPoint)
                .then().log().all()
                .assertThat().statusCode(200).extract().response();

        Board.setListID1(re.path("[0].id"));
        Board.setListID2(re.path("[1].id"));
        Board.setListID3(re.path("[2].id"));
    }

    @Test(priority = 4)
    public void updateList () {
        // end point customization
        String endPoint = URLs.lists+Board.getListID1();

        given().baseUri(URLs.BaseURL)
                .queryParams(AuthCredentials.getAuthParams())
                .queryParams("name", faker.job().field())
                .when().put(endPoint)
                .then().log().all()
                .assertThat().statusCode(200);

        // end point customization
        String endPoint2 = URLs.lists+Board.getListID2();

        given().baseUri(URLs.BaseURL)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .queryParams(AuthCredentials.getAuthParams())
                .queryParam("name", faker.job().field())
                .when().put(endPoint2)
                .then().log().all()
                .assertThat().statusCode(200);

    }

    @Test(priority = 5)
    public void createAndArchiveList () {

        // -------------Creating List---------------
        // create list name
        String fakeCat = faker.cat().name();
        System.out.println(fakeCat);

        // create list
        Response re = given().baseUri(URLs.BaseURL)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .queryParams(AuthCredentials.getAuthParams())
                .queryParam("name", fakeCat)
                .queryParam("idBoard", Board.getId())
                .when().post(URLs.lists)
                .then().log().all()
                .assertThat().statusCode(200).extract().response();

        // store list id
        String newListID = re.path("id");

        // -------------Archiving List---------------
        // end point customization
        String endPoint2 = URLs.lists+newListID+"/closed/";

        given().baseUri(URLs.BaseURL)
                .queryParams(AuthCredentials.getAuthParams())
                .queryParam("value", "true")
                .when().put(endPoint2)
                .then().log().all()
                .assertThat().statusCode(200).extract().response();
    }

    // to be used in nest test case
    String cardId ;

    @Test(invocationCount = 5, priority = 6)
    public void createAndUpdateCard() throws InterruptedException {
        // here we run 2 test cases 5 times one after another
        createCard();
        updateCard();
    }

    public void createCard () throws InterruptedException {

        Response re = given().baseUri(URLs.BaseURL)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .queryParams(AuthCredentials.getAuthParams())
                .queryParam("idList", Board.getListID1())
                .queryParam("name", faker.commerce().material())
                //.queryParam("desc", faker.demographic().race())
                .when().post(URLs.cards)
                .then().log().all()
                .assertThat().statusCode(200).extract().response();

        // to be used in the updateCard test case
        cardId = re.path("id");
    }

    public void updateCard () throws InterruptedException {

        // small delay, so you can see the cards moving on the website
        // comment this command if you are focusing on actual test results
        TimeUnit.SECONDS.sleep(30);

        // end point customization
        String endPoint = URLs.cards+cardId;

        given().baseUri(URLs.BaseURL)
                .header("Accept", "application/json")
                .queryParams(AuthCredentials.getAuthParams())
                .queryParam("idList", Board.getListID2())
                .queryParam("desc", faker.animal().name())
                .when().put(endPoint)
                .then().log().all()
                .assertThat().statusCode(200);
    }
}