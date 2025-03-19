package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.authentication.AuthenticationProvider;
import com.rayan.salarytracker.authentication.AuthenticationResponseDTO;
import com.rayan.salarytracker.core.exception.AppServerException;
import com.rayan.salarytracker.core.exception.EntityAlreadyExistsException;
import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.core.util.validation.ValidatorUtil;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserLoginDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.security.jwt.JwtUtil;
import com.rayan.salarytracker.service.UserService;
import jakarta.annotation.security.PermitAll;
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
    @Inject
    AuthenticationProvider authenticationProvider;

    @GET
    public Response getAllUsers() {
        List<User> userList = userService.getUsers();
        return Response.status(Response.Status.CREATED)
                .entity(userList)
                .build();
    }

    @POST
    @Path("/register")
    @PermitAll
    public Response registerUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException, AppServerException {
        // Validation
        validatorUtil.validateDTO(userInsertDTO);
        UserReadOnlyDTO userReadOnlyDTO = userService.createUser(userInsertDTO);
        return Response.status(Response.Status.OK)
                .entity(userReadOnlyDTO)
                .build();
    }
    @POST
    @Path("/login")
    @PermitAll
    public Response loginUser(UserLoginDTO userLoginDTO) throws EntityInvalidArgumentsException, EntityNotFoundException {
        System.out.println("============ inside login ============");
        // Authentication
        boolean isAuthenticated = authenticationProvider.authenticate(userLoginDTO);
        if (!isAuthenticated) {
            throw new EntityInvalidArgumentsException("User", "email or password incorrect");
        }

        // Generate JWT
        UserReadOnlyDTO userReadOnlyDTO = userService.findUserByEmail(userLoginDTO.getEmail());
        String email = userReadOnlyDTO.getEmail();
        String role = userReadOnlyDTO.getRole();
        String token = JwtUtil.generateToken(email, role);
        System.out.println("Generated Token: " + token);

        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO(token, userReadOnlyDTO);
        return Response.status(Response.Status.OK).entity(authenticationResponseDTO).build();
    }

}
