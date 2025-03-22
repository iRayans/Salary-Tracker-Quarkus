package com.rayan.salarytracker.service;

import com.rayan.salarytracker.core.exception.AppServerException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.model.User;

import java.util.List;

public interface IUserService {
    UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) throws AppServerException;
    UserReadOnlyDTO findUserByEmail(String email) throws EntityNotFoundException;
    List<User> getUsers();
    UserReadOnlyDTO getUserById(Long id) throws EntityNotFoundException;
    Boolean isEmailExists(String email);
    Boolean isUserValid(String email, String password);
}
