package ApiAssingment;

//import static org.junit.Assert.assertTrue;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class GetBooking 
{
	@BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void testGetBooking() {
        // Perform a GET request to retrieve booking information
        Response response = RestAssured.get("/booking/2414"); // Replace "1" with the actual booking ID

        // Validate the response
        response.then()
            .statusCode(200) // Assuming a successful request returns a 200 status code
            .extract()
            .response()
            .prettyPrint(); // Print the response body for verification

        // Add additional assertions as needed
    }
}