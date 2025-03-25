package com.rayan.salarytracker.service.impl;


import com.rayan.salarytracker.core.exception.EntityAlreadyExistsException;
import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.salary.SalaryInsertDTO;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;
import com.rayan.salarytracker.dto.salary.SalaryUpdateRequestDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.Salary;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.repository.IUserRepository;
import com.rayan.salarytracker.repository.impl.SalaryRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import com.rayan.salarytracker.service.ISalaryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.util.*;

@ApplicationScoped
@Transactional
public class SalaryService implements ISalaryService {
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
    IUserRepository userRepository;


    @Override
    public List<SalaryReadOnlyDTO> findAllSalaries(int year) throws EntityNotFoundException {
        Long userId = loggedInUser.getUserId();
        LOGGER.info("Find salary by id: " + userId);
        List<Salary> salaries = salaryRepository.findSalaryByUserId(userId, year);
        List<SalaryReadOnlyDTO> salaryReadOnlyDTO = salaries.stream().map(mapper::mapToSalaryReadOnlyDTO).toList();
        if (salaries.isEmpty()) {
            LOGGER.info("No salaries found for id: " + userId);
            throw new EntityNotFoundException("Salary", "Salary not found");
        }
        LOGGER.info("Found " + salaries.size() + " salaries");
        return salaryReadOnlyDTO;
    }

    @Override
    public SalaryReadOnlyDTO findSalaryById(Long salaryId) throws EntityNotFoundException {
        LOGGER.info("Find salary by id: " + salaryId);
        Salary salary = findById(salaryId);
        LOGGER.info("Found " + salary);
        return mapper.mapToSalaryReadOnlyDTO(salary);
    }

    @Override
    public SalaryReadOnlyDTO createSalary(SalaryInsertDTO salaryInsertDTO) throws EntityInvalidArgumentsException, EntityAlreadyExistsException {
        LOGGER.info("Saving Salary...");
        // set current year.
        User user = userRepository.findUserByEmail(loggedInUser.getUserEmail());
        Salary salary = mapper.mapToSalary(salaryInsertDTO);
        salary.setYear(LocalDateTime.now().getYear());
        salary.setUser(user);
        if (!validateMonth(salaryInsertDTO.getMonth())) {
            LOGGER.error("Invalid month: " + salaryInsertDTO.getMonth());
            throw new EntityInvalidArgumentsException("Salary", "Enter Valid Moth Only");
        }
        // Check if the month already exists
        if (salaryRepository.salaryExistsForUserInMonthAndYear(salary.getUser().getId(), salaryInsertDTO.getMonth(), salary.getYear())) {
            LOGGER.error("Salary for month {} already exists." + salaryInsertDTO.getMonth());
            throw new EntityAlreadyExistsException("Salary", "Salary with month " + salaryInsertDTO.getMonth() + " already exists.");
        }
        LOGGER.info("Salary saved: " + salary.toString());
        salaryRepository.persist(salary);
        return mapper.mapToSalaryReadOnlyDTO(salary);
    }

    @Override
    public SalaryReadOnlyDTO updateSalary(Long salaryId, SalaryUpdateRequestDTO salaryUpdateRequest) throws EntityNotFoundException {
        // get existing Salary
        LOGGER.info("Updating Salary...");
        Salary salary = findById(salaryId);
        updateFields(salary, salaryUpdateRequest);
        LOGGER.info("Salary updated: " + salaryUpdateRequest);
        return mapper.mapToSalaryReadOnlyDTO(salary);
    }

    @Override
    public void deleteSalaryById(Long salaryId) throws EntityNotFoundException {
        Salary salary = findById(salaryId);
        LOGGER.info("Deleting Salary...");
        salaryRepository.delete(salary);
    }


    // ######### Helper methods #########

    private Salary findById(Long salaryId) throws EntityNotFoundException {
        Salary salary = salaryRepository.findById(salaryId);
        if (salary == null) {
            LOGGER.error("Salary not found for id: " + salaryId);
            throw new EntityNotFoundException("Salary", "Salary not found");
        }
        LOGGER.info("Found " + salary);
        return salary;
    }

    private boolean validateMonth(String month) {
        return VALID_MONTHS.contains(month);
    }

    private void updateFields(Salary existing, SalaryUpdateRequestDTO updated) {
        LOGGER.info("Updating salary fields...");
        existing.setAmount(updated.getAmount() != 0 ? updated.getAmount() : existing.getAmount());
        existing.setDescription(updated.getDescription() != null ? updated.getDescription() : existing.getDescription());

    }
}
