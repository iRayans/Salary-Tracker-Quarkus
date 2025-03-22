package com.rayan.salarytracker.service;

import com.rayan.salarytracker.core.exception.EntityAlreadyExistsException;
import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.salary.SalaryInsertDTO;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;
import com.rayan.salarytracker.dto.salary.SalaryUpdateRequest;

import java.util.List;

public interface ISalaryService {
    List<SalaryReadOnlyDTO> findAllSalaries() throws EntityNotFoundException;
    SalaryReadOnlyDTO findSalaryById(Long salaryId) throws EntityNotFoundException;
    SalaryReadOnlyDTO createSalary(SalaryInsertDTO salaryInsertDTO) throws EntityInvalidArgumentsException, EntityAlreadyExistsException;
    SalaryReadOnlyDTO updateSalary(Long salaryId, SalaryUpdateRequest salaryUpdateRequest) throws EntityNotFoundException;
    public void deleteSalaryById(Long salaryId) throws EntityNotFoundException;
}
