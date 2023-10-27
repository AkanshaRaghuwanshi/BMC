package ApiAssingment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;

public class CreateBooking 
{
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        
    }

    @Test
    public void testCreateBooking() throws JsonProcessingException {
    	
        // Create an instance of the Person class
        Pojo person = new Pojo();
      // Create an ObjectMapper from the Jackson library
        ObjectMapper objectMapper = new ObjectMapper();

        // Serialize the Person object to JSON
        String json = objectMapper.writeValueAsString(person);
        // Create your request, send it, and validate the response
        
        Response response = RestAssured.given()
            .contentType("application/json")
            .body(json)
            .post("/booking");

        // Assert the response status code
        response.then()
            .statusCode(200);

        // Parse the response body
        JsonPath jsonPath = response.jsonPath();
        
        // Extract the booking ID
        int bookingId = jsonPath.getInt("bookingid");

        // Print the booking ID
        System.out.println("Booking ID: " + bookingId);

        // Add additional assertions or validations as needed
        // For example, you can assert that the booking ID is not null or greater than zero
        Assert.assertNotNull(bookingId, "Booking ID should not be null");
        Assert.assertTrue(bookingId > 0, "Booking ID should be greater than zero");
    }
}