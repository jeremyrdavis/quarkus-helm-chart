package io.arrogantprogrammer;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.Random;

@Path("/hello")
public class GreetingResource {

    private static final List<String> greetings = List.of(
        "Hello", // English
        "Hola", // Spanish
        "Bonjour", // French
        "Ciao", // Italian
        "Konnichiwa" // Japanese
    );

    private final Random random = new Random();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greetings.get(random.nextInt(greetings.size()));
    }

    public static List<String> getGreetings() {
        return greetings;
    }
}