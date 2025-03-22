package com.rayan.salarytracker.repository.impl;

import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.repository.IUserRepository;
import com.rayan.salarytracker.security.PasswordUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> , IUserRepository {

    @Override
    public User findUserByEmail(String email) {
        return find("email", email).firstResult();
    }
    @Override
    public Boolean isEmailExists(String email) {
        return count("email", email) > 0;
    }
    @Override
    public Boolean isUserValid(String email, String plainPassword) {
        User user =  find("email = ?1", email).firstResult();
        return PasswordUtil.checkPassword(plainPassword,user.getPassword());
    }
}
