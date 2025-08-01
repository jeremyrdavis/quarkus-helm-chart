package io.arrogantprogrammer;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/hello")
public class GreetingResource {

    @Inject
    @RestClient
    io.arrogantprogrammer.client.HelmBackService helmBackService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("name") String name) {
        String backendGreeting = helmBackService.getGreeting();
        Log.debugf("HELM BACK SERVICE: %s", backendGreeting);

        if (name == null || name.isEmpty()) {
            Log.debug("Empty name");
            return "Quarkus says, '" + backendGreeting + "'";
        } else {
            return backendGreeting + ", " + name + "!";
        }
    }
}