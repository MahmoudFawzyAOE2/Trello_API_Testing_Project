package TestSuite.BaseTest;

// local classes import
import EndPoints.URLs;
import UserData.AuthCredentials;
import PojoClasses.BoardPojo;

// Not Testing Imports
import com.github.javafaker.Faker;

// Testing Imports
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;

// Static Imports
import static io.restassured.RestAssured.*;


public class Base {

    public static BoardPojo Board = new BoardPojo() ;
    public Faker faker = new Faker();

    public RequestSpecification request;
    @BeforeClass
    @Description("adding necessary Headers to the requests")
    public void beforeClass (){
        request = given().baseUri(URLs.BaseURL).queryParams(AuthCredentials.getAuthParams())
                .header("Accept", "application/json");

    }
}


