package com.rayan.salarytracker.repository;

import com.rayan.salarytracker.model.Salary;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.security.PasswordUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User findUserByEmail(String email) {
        return find("email", email).firstResult();
    }
    public Boolean isEmailExists(String email) {
        return count("email", email) > 0;
    }

    public Boolean isUserValid(String email, String plainPassword) {
        User user =  find("email = ?1", email).firstResult();
        return PasswordUtil.checkPassword(plainPassword,user.getPassword());
    }
}
