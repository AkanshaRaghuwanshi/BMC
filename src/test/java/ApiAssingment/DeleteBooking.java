package ApiAssingment;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBooking {

    @Test
    public void testDeleteBooking() {
        // Set the base URL for the Restful Booker API
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Perform a DELETE request to delete a booking by its ID
        String bookingIdToDelete = "1206"; // Replace with the actual booking ID
        Response response = RestAssured.given()
        		.contentType("application/json")
                .when()
                .delete("/booking/" + bookingIdToDelete);

        // Verify the response
        int statusCode = response.getStatusCode();

        // Assert that the response status code is 201 (indicating successful deletion)
        Assert.assertEquals(statusCode, 201, "Booking deletion failed");

        // You can add further assertions or validations as needed
    }
}