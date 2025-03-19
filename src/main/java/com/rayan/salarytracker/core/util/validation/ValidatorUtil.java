package com.rayan.salarytracker.core.util.validation;

import com.rayan.salarytracker.core.exception.EntityAlreadyExistsException;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

@ApplicationScoped
public class ValidatorUtil {
    private static final Logger LOGGER = Logger.getLogger(ValidatorUtil.class);

    @Inject
    UserService userService;
    public void validateDTO(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException {
        if (userService.isEmailExists(userInsertDTO.getEmail())) {
            LOGGER.error("Email already exists");
            throw new EntityAlreadyExistsException("User ", "User with Email: " + userInsertDTO.getEmail() + " is Already exist");
        }
    }
}
