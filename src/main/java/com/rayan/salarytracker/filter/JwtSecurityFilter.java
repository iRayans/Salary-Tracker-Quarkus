package com.rayan.salarytracker.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.Set;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtSecurityFilter  implements ContainerRequestFilter {
    private final static Set<String> PUBLIC_PATHS = Set.of(
            "/auth/register",
            "/auth/login");

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        UriInfo uriInfo = requestContext.getUriInfo();
        // Check if the path is public
        if (isPublicPath(uriInfo.getPath())) {
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(jakarta.ws.rs.core.Response.status(401)
                    .entity("{\"error\": \"Missing or invalid JWT\"}")
                    .build());
        }}

    // Checks if a path belongs to public paths
    private boolean isPublicPath(String path) {
        System.out.println("Path: " + path);
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }
}
