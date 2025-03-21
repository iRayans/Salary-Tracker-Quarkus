package com.rayan.salarytracker.service;


import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.salary.SalaryInsertDTO;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.Salary;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.repository.SalaryRepository;
import com.rayan.salarytracker.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.util.*;

@ApplicationScoped
@Transactional
public class SalaryService {
    private static final Logger LOGGER = Logger.getLogger(SalaryService.class);

    private static final List<String> VALID_MONTHS = List.of(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );
    @Inject
    SalaryRepository salaryRepository;
    @Inject
    Mapper mapper;
    @Inject
    JsonWebToken jwt;
    @Inject
    UserRepository userRepository;


    public List<SalaryReadOnlyDTO> findById(Long id) throws EntityNotFoundException {
        LOGGER.info("Find salary by id: " + id);
        List<Salary> salaries = salaryRepository.findSalaryByUserId(id, 2025);
        List<SalaryReadOnlyDTO> salaryReadOnlyDTO = salaries.stream().map(mapper::mapToSalaryReadOnlyDTO).toList();
        if (salaries.isEmpty()) {
            LOGGER.info("No salaries found for id: " + id);
            throw new EntityNotFoundException("Salary", "Salary not found");
        }
        LOGGER.info("Found " + salaries.size() + " salaries");
        return salaryReadOnlyDTO;
    }

    public SalaryReadOnlyDTO createSalary(SalaryInsertDTO salaryInsertDTO) throws EntityInvalidArgumentsException, EntityNotFoundException {
        LOGGER.info("Save salary: " + salaryInsertDTO);
        salaryInsertDTO.setYear(LocalDateTime.now().getYear());
        salaryInsertDTO.setUser(getLoggedInUser());
        Salary salary = mapper.mapToSalary(salaryInsertDTO);

        if(!validateMonth(salaryInsertDTO.getMonth())) {
            LOGGER.error("Invalid month: " + salaryInsertDTO.getMonth());
            throw new EntityInvalidArgumentsException("Salary", "Enter Valid Moth Only");
        }
        LOGGER.info("Save salary: " + salary);
        salaryRepository.persist(salary);
        return mapper.mapToSalaryReadOnlyDTO(salary);

    }

    private boolean validateMonth(String month) {
        return VALID_MONTHS.contains(month);
    }

    private User getLoggedInUser() throws EntityNotFoundException {
        LOGGER.info("Logged in user: " + jwt.getClaim("userId"));
        // Cast claim `JsonNumber → Long`
        Long userId = ((JsonNumber) jwt.getClaim("userId")).longValue();

        return userRepository.findById(userId);
    }

}
