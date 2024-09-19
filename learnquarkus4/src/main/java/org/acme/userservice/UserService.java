package org.acme.userservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org1.acme.user.DTO.UserTO;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8080/user")
public interface UserService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<UserTO> getUsersI();
}
