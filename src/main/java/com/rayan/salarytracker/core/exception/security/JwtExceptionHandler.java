package com.rayan.salarytracker.core.exception.security;

import io.quarkus.security.AuthenticationFailedException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class JwtExceptionHandler implements ExceptionMapper<AuthenticationFailedException> {
    private static final Logger LOGGER = Logger.getLogger(JwtExceptionHandler.class);

    @Override
    public Response toResponse(AuthenticationFailedException exception) {
        LOGGER.error("Authentication failed");
        return Response.status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .entity("{\"error\": \"Invalid or expired JWT token\"}")
                .build();
    }
}
