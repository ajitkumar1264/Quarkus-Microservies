package org1.acme.user.application;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org1.acme.user.service.UserService;
import org1.acme.user.DTO.UserTO;
import org1.acme.user.exception.UserException;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserAPI {

    private static final Logger log = LoggerFactory.getLogger(UserAPI.class);
    @Inject
    UserService userService;

    @POST
    @Path("/addUser")
    public Response addUser(UserTO user)
    {
        if (user == null) {
            throw new IllegalArgumentException("can not get the value of body!");
        }
        try {

            UserTO addednew = userService.addUser(user);
            return Response.status(Response.Status.CREATED).entity(addednew).build();
        }
        catch (UserException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error!").build();
        }
    }

    @GET
    public Response getUsers()
    {
        try{

         List<UserTO> AllUsers=userService.getUsers();
         return  Response.status(Response.Status.OK).entity(AllUsers).build();

        }
        catch (UserException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server Error!").build();
        }


    }

    @DELETE
    @Path("/{Id}")
    public Response deleteUser(@PathParam("Id") long Id)
    {
        try{
            userService.deleteUser(Id);
            return Response.status(Response.Status.OK).entity("user Deleted Successfully!").build();
        }
        catch (UserException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
        catch(Exception e)
        {
            return Response.status(Response.Status.OK).entity("Internal Server Error!").build();
        }
    }

    @PUT
    @Path("/{Id}")
    public Response updateUser(@PathParam("Id") long Id,UserTO user)
    {
        try{
             UserTO UpdatedUser=userService.updateUser(Id,user);
            return Response.status(Response.Status.OK).entity(UpdatedUser).build();
        }
        catch (UserException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
        catch(Exception e)
        {
            return Response.status(Response.Status.OK).entity("Internal Server Error!").build();
        }
    }

}


