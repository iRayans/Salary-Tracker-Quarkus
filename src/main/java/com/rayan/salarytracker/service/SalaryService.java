package com.rayan.salarytracker.service;


import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.salary.SalaryInsertDTO;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;
import com.rayan.salarytracker.dto.salary.SalaryUpdateRequest;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.Salary;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.repository.SalaryRepository;
import com.rayan.salarytracker.repository.UserRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.util.*;

@ApplicationScoped
@Transactional
public class SalaryService implements IUserService {
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
    LoggedInUser loggedInUser;
    @Inject
    UserRepository userRepository;


    @Override
    public List<SalaryReadOnlyDTO> findAllSalaries() throws EntityNotFoundException {
        Long userId = loggedInUser.getUserId();
        LOGGER.info("Find salary by id: " + userId);
        List<Salary> salaries = salaryRepository.findSalaryByUserId(userId, 2025);
        List<SalaryReadOnlyDTO> salaryReadOnlyDTO = salaries.stream().map(mapper::mapToSalaryReadOnlyDTO).toList();
        if (salaries.isEmpty()) {
            LOGGER.info("No salaries found for id: " + userId);
            throw new EntityNotFoundException("Salary", "Salary not found");
        }
        LOGGER.info("Found " + salaries.size() + " salaries");
        return salaryReadOnlyDTO;
    }

    @Override
    public SalaryReadOnlyDTO findSalaryById(Long id) throws EntityNotFoundException {
        LOGGER.info("Find salary by id: " + id);
        Salary salary = salaryRepository.findById(id);
        if (salary == null) {
            throw new EntityNotFoundException("Salary", "Salary not found");
        }
        LOGGER.info("Found " + salary);
        return mapper.mapToSalaryReadOnlyDTO(salary);
    }

    @Override
    public SalaryReadOnlyDTO createSalary(SalaryInsertDTO salaryInsertDTO) throws EntityInvalidArgumentsException, EntityNotFoundException {
        LOGGER.info("Saving Salary...");
        User user = userRepository.findById(loggedInUser.getUserId());
        salaryInsertDTO.setYear(LocalDateTime.now().getYear());
        salaryInsertDTO.setUser(user);
        Salary salary = mapper.mapToSalary(salaryInsertDTO);

        if (!validateMonth(salaryInsertDTO.getMonth())) {
            LOGGER.error("Invalid month: " + salaryInsertDTO.getMonth());
            throw new EntityInvalidArgumentsException("Salary", "Enter Valid Moth Only");
        }
        LOGGER.info("Salary saved: " + salary.toString());
        salaryRepository.persist(salary);
        return mapper.mapToSalaryReadOnlyDTO(salary);
    }

    @Override
    public SalaryReadOnlyDTO updateSalary(Long salaryId, SalaryUpdateRequest salaryUpdateRequest) throws EntityNotFoundException {
        // get existing Salary
        LOGGER.info("Updating Salary...");
        Salary salary = salaryRepository.findById(salaryId);
        if(salary == null) {
            LOGGER.error("Salary not found for id: " + salaryId);
            throw new EntityNotFoundException("Salary", "Salary not found");
        }
        updateFields(salary, salaryUpdateRequest);
        LOGGER.info("Salary updated: " + salaryUpdateRequest);
        return mapper.mapToSalaryReadOnlyDTO(salary);
    }

    private boolean validateMonth(String month) {
        return VALID_MONTHS.contains(month);
    }

    private void updateFields(Salary existing, SalaryUpdateRequest updated){
        LOGGER.info("Updating salary fields...");
        existing.setAmount(updated.getAmount() != 0 ? updated.getAmount() : existing.getAmount());
        existing.setDescription(updated.getDescription() != null ? updated.getDescription() : existing.getDescription());

    }
}
