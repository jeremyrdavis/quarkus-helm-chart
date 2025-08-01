package io.arrogantprogrammer;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        // Get the response as a String
        String response = given()
            .when().get("/hello")
            .then()
                .statusCode(200)
                .extract().asString();

        // Verify the response is in the list of greetings
        assertTrue(GreetingResource.getGreetings().contains(response),
                "Response should be one of the valid greetings");
    }
}