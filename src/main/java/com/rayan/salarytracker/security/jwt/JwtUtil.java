package com.rayan.salarytracker.security.jwt;


import io.smallrye.jwt.build.Jwt;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to generate a JWT token.
 */

public class JwtUtil {
    public static String generateToken(String email, String role) {
        Set<String> roles = new HashSet<>();
        roles.add(role);

        return Jwt.issuer("my-auth-service")
                .subject(email)
                .groups(roles)
                .expiresIn(Duration.ofHours(2)) // Token valid for 2 hours
                .sign();
    }

}
