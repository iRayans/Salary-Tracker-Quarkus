package com.rayan.salarytracker.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class UserInsertDTO {

    public UserInsertDTO(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserInsertDTO() {

    }

    @Pattern(regexp = "\\w{2,30}", message = "Username must be 2-30 long and contain only letters, digits, or underscores.")
    private String username;

    @Email(message = "Please enter a valid email")
    private String email;

    // Validates password: at least 8 characters, includes lowercase, uppercase,
    // digit, and special character (!@#%&*).
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[!@#%&*]).{8,}$", message = "Invalid password.")
    private String password;

//    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[!@#%&*]).{8,}$", message = "Invalid password.")
//    private String confirmPassword;

    @NotEmpty(message = "Role must not be empty.")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
