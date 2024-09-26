package TestSuite.BaseTest;

// local classes import
import EndPoints.URLs;
import UserData.AuthCredentials;
import PojoClasses.BoardPojo;

// Not Testing Imports
import com.github.javafaker.Faker;

// Testing Imports
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.testng.annotations.*;

// Static Imports
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.isOneOf;


public class Base {

    public static BoardPojo Board = new BoardPojo() ;
    public Faker faker = new Faker();

    public RequestSpecification request;
    @BeforeMethod
    @Description("adding necessary Headers to the requests")
    public void beforeMethod (){
        request = given().baseUri(URLs.BaseURL).queryParams(AuthCredentials.getAuthParams())
                .header("Accept", "application/json");
    }

    @BeforeSuite
    @Description("adding necessary Headers to the requests")
    public void beforeClass (){
        request = given().baseUri(URLs.BaseURL).queryParams(AuthCredentials.getAuthParams())
                .header("Accept", "application/json");
    }

    @AfterSuite ()
    @Description("adding necessary Headers to the requests")
    public void afterSuite (){

        System.out.println("Request: " + request);
        Response re = given().spec(request)
                .pathParam("id", Board.getId())
                .when().delete(URLs.boards_id)
                .then().log().all()
                .assertThat().statusCode(isOneOf(200, 404) ).extract().response();

        if (re.statusCode() == 200 || re.statusCode() == 404) {
            System.out.println("\n\n*************************** Board is Deleted Successfully ***************************");
        }
        else {
            System.out.println("\n\n########################### Board is NOT Deleted ###########################");
        }

        System.out.println("\n\n***************************Test Suite Ends Successfully***************************");

    }
}


