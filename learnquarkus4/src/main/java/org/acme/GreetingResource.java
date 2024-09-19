package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.userservice.UserService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org1.acme.user.DTO.UserTO;

import java.util.List;

@Path("/hello")
public class GreetingResource {

    @Inject
    @RestClient
    UserService userService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        List<UserTO> allusers=userService.getUsersI();
        return Response.status(Response.Status.OK).entity(allusers).build();
    }
}
