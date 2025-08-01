package io.arrogantprogrammer;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class GreetingResourceTest {

    @InjectMock
    @RestClient
    io.arrogantprogrammer.client.HelmBackService helmBackService;

    @BeforeEach
    void setup() {
        Mockito.when(helmBackService.getGreeting()).thenReturn("Hello");
    }

    @Test
    void testHelloEndpointWithoutName() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(containsString("Quarkus says, 'Hello'"));
    }

    @Test
    void testHelloEndpointWithName() {
        String testName = "Tester";

        given()
          .queryParam("name", testName)
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(containsString("Hello, " + testName + "!"));
    }
}