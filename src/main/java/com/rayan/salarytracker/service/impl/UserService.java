package com.rayan.salarytracker.service.impl;

import com.rayan.salarytracker.service.IUserService;
import jakarta.persistence.PersistenceException;

import com.rayan.salarytracker.core.exception.AppServerException;
import com.rayan.salarytracker.core.exception.EntityAlreadyExistsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.repository.impl.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
@Transactional
public class UserService implements IUserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @Inject
    UserRepository userRepository;
    @Inject
    Mapper mapper;

    @Override
    public UserReadOnlyDTO createUser(UserInsertDTO userInsertDTO) throws AppServerException {
        try {
            LOGGER.info("Creating user: " + userInsertDTO);
            if (userRepository.isEmailExists(userInsertDTO.getEmail())) {
                LOGGER.error("Email already exists");
                throw new EntityAlreadyExistsException("User", "User already exists!");
            }
            if (userInsertDTO.getRole() == null) {
                userInsertDTO.setRole("USER");
            }

            User user = mapper.mapToUser(userInsertDTO);
            userRepository.persist(user);
            LOGGER.info("User created.");
            return mapper.mapToUserReadOnlyDTO(user);
        } catch (PersistenceException e) {
            LOGGER.error(e);
            throw new AppServerException("User", "Unexpected database error occurred.");
        } catch (Exception e) {
            LOGGER.error(e);
            throw new AppServerException("User", "An unexpected error occurred.");
        }

    }

    @Override
    public UserReadOnlyDTO findUserByEmail(String email) throws EntityNotFoundException {
        LOGGER.info("Find user by email: " + email);
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            LOGGER.error("User not found");
            throw new EntityNotFoundException("User", "User with email '" + email + "' not found.");
        }
        LOGGER.info("User found.");
        return mapper.mapToUserReadOnlyDTO(user);
    }
    @Override
    public List<User> getUsers() {
        LOGGER.info("Get all users");
        return userRepository.listAll();
    }
    @Override
    public UserReadOnlyDTO getUserById(Long id) throws EntityNotFoundException {
        LOGGER.info("Get user by id: " + id);
        User user = userRepository.findById(id);

        if (user == null) {
            LOGGER.error("User not found");
            throw new EntityNotFoundException("User", "User not found.");
        }
        LOGGER.info("User found.");
        return mapper.mapToUserReadOnlyDTO(user);
    }
    @Override
    public Boolean isEmailExists(String email) {
        LOGGER.info("Check if user with email: " + email + " exists");
        return userRepository.isEmailExists(email);
    }
    @Override
    public Boolean isUserValid(String email, String password) {
        LOGGER.info("Check if user with email: " + email + " exists");
        return userRepository.isUserValid(email, password);
    }
}
