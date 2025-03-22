package com.rayan.salarytracker.repository;

import com.rayan.salarytracker.model.User;

public interface IUserRepository  {
    User findUserByEmail(String email);

    Boolean isEmailExists(String email);

    Boolean isUserValid(String email, String plainPassword);
}
