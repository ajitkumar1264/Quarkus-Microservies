package org1.acme.user.application;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import org1.acme.user.DTO.UserTO;
import org.apache.http.HttpStatus;

import java.util.List;

@QuarkusTest
public class UserapiTest {

    @InjectMock
    org1.acme.user.service.UserService UserService;

    @Test
    public void TestAddUserEndPoint() {

        UserTO NewUser = new UserTO("john doe", "ajit@gmail.com", "7096525061");


        when(UserService.addUser(eq(NewUser))).thenReturn(NewUser);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(NewUser)
                .when()
                .post("/user/addUser");

        response.then().statusCode(HttpStatus.SC_CREATED);

        response.then()
                .body("name", is("john doe"))
                .body("emailId", is("ajit@gmail.com"))
                .body("mobileNo", is("7096525061"));


    }


    @Test
    public void TestGetUserEndPoint() {
        List<UserTO> users = List.of(
                new UserTO("ajit", "ajit@gmail.com", "7096525061"),
                new UserTO("dilip", "dilip@gmail.com", "8980525061")
        );

        when(UserService.getUsers()).thenReturn(users);

        Response response = given().contentType(ContentType.JSON).when().get("/user");

        response.then().statusCode(HttpStatus.SC_OK);

        response.then()
                .body("[0].name", is("ajit"))
                .body("[0].emailId", is("ajit@gmail.com"))
                .body("[0].mobileNo", is("7096525061"));

    }

    @Test
    public void TestUpdateUserEndPoint() {

        long userId = 1L;
        UserTO UpdatedUser = new UserTO("ajit", "ajit@gmail.com", "7096525061");
        UserTO NewUser = new UserTO("hardik", "ajit@gmail.com", "7096525061");

        when(UserService.updateUser(userId, UpdatedUser)).thenReturn(NewUser);

        Response response = given().contentType(ContentType.JSON).body(UpdatedUser)
                .when().put("/user/" + userId);

        response.then().statusCode(HttpStatus.SC_OK);

        response.then().statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode())
                .body("name", is("hardik"))
                .body("emailId", is("ajit@gmail.com"))
                .body("mobileNo", is("7096525061"));
    }

    @Test
    public void TestDeleteUserEndPoint() {
        long userId = 1L;

        doNothing().when(UserService).deleteUser(userId);

        Response response = given().
                when().delete("/user/" + userId);

        response.then().statusCode(HttpStatus.SC_OK);

        response.then().body(is("user Deleted Successfully!"));
    }
}
