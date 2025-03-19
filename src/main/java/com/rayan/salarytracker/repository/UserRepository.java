package com.rayan.salarytracker.repository;

import com.rayan.salarytracker.model.Salary;
import com.rayan.salarytracker.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Boolean isEmailExists(String email) {
        return count("email", email) > 0;
    }
}
