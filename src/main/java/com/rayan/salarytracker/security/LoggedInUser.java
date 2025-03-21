package com.rayan.salarytracker.security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import org.eclipse.microprofile.jwt.JsonWebToken;

@RequestScoped
public class LoggedInUser {

    @Inject
    JsonWebToken jwt;

    public Long getUserId() {
        // Cast claim `JsonNumber → Long`
         return ((JsonNumber) jwt.getClaim("userId")).longValue();
    }

    public String getUserEmail() {
        return jwt.getSubject();
    }

    public boolean hasRole(String role) {
        return jwt.getGroups().contains(role);
    }
}
