package com.rayan.salarytracker.core.exception.security;

import com.rayan.salarytracker.service.UserService;
import io.quarkus.security.ForbiddenException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class ForbiddenExceptionHandler implements ExceptionMapper<ForbiddenException> {

    private static final Logger LOGGER = Logger.getLogger(ForbiddenExceptionHandler.class);

    @Override
    public Response toResponse(ForbiddenException exception) {
        LOGGER.error("Forbidden Exception");
        return Response.status(Response.Status.FORBIDDEN)
                .type(MediaType.APPLICATION_JSON)
                .entity("{\"error\": \"Access denied. You do not have the required permissions.\"}")
                .build();
    }
}