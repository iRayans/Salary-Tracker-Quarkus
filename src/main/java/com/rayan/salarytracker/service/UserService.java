package com.rayan.salarytracker.service;

import org.hibernate.exception.ConstraintViolationException;
import jakarta.persistence.PersistenceException;

import com.rayan.salarytracker.core.exception.AppServerException;
import com.rayan.salarytracker.core.exception.EntityAlreadyExistsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class UserService {

    @Inject
    UserRepository userRepository;
    @Inject
    Mapper mapper;


    public UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException, AppServerException {
        try {
            if (userRepository.isEmailExists(userInsertDTO.getEmail())) {
                throw new EntityAlreadyExistsException("User", "User already exists!");
            }
            if (userInsertDTO.getRole() == null) {
                userInsertDTO.setRole("USER");
            }

            User user = mapper.mapToUser(userInsertDTO);
            userRepository.persist(user);

            return mapper.mapToUserReadOnlyDTO(user);
        } catch (PersistenceException e) {
            throw new AppServerException("User", "Unexpected database error occurred.");
        } catch (Exception e) {
            throw new AppServerException("User", "An unexpected error occurred.");
        }

    }

    public UserReadOnlyDTO findUserByEmail(String email) throws EntityNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            throw new EntityNotFoundException("User", "User with email '" + email + "' not found.");
        }
        return mapper.mapToUserReadOnlyDTO(user);
    }

    public List<User> getUsers() {
        return userRepository.listAll();
    }

    public UserReadOnlyDTO getUserById(Long id) throws EntityNotFoundException {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new EntityNotFoundException("User", "User not found.");
        }
        return mapper.mapToUserReadOnlyDTO(user);
    }

    public Boolean isEmailExists(String email) {
        return userRepository.isEmailExists(email);
    }

    public Boolean isUserValid(String email, String password) {
        return userRepository.isUserValid(email, password);
    }
}
