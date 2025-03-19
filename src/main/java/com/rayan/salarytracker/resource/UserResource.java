package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.core.exception.AppServerException;
import com.rayan.salarytracker.core.exception.EntityAlreadyExistsException;
import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.core.util.validation.ValidatorUtil;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserLoginDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;
    @Inject
    ValidatorUtil validatorUtil;

    @GET
    public Response getAllUsers() {
        List<User> userList = userService.getUsers();
        return Response.status(Response.Status.CREATED)
                .entity(userList)
                .build();
    }

    @POST
    @Path("/register")
    public Response registerUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException, AppServerException {
        // Validation
        validatorUtil.validateDTO(userInsertDTO);
        UserReadOnlyDTO userReadOnlyDTO = userService.createUser(userInsertDTO);
        return Response.status(Response.Status.OK)
                .entity(userReadOnlyDTO)
                .build();
    }


}
