package com.rayan.salarytracker.service;

import com.rayan.salarytracker.core.exception.AppServerException;
import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.salary.SalaryInsertDTO;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;
import com.rayan.salarytracker.dto.salary.SalaryUpdateRequest;
import com.rayan.salarytracker.model.Salary;

import java.util.List;

public interface IUserService {

    List<SalaryReadOnlyDTO> findAllSalaries() throws EntityNotFoundException;

    SalaryReadOnlyDTO findSalaryById(Long id) throws EntityNotFoundException;

    SalaryReadOnlyDTO createSalary(SalaryInsertDTO salaryInsertDTO) throws EntityInvalidArgumentsException, EntityNotFoundException;

    SalaryReadOnlyDTO updateSalary(Long salaryId, SalaryUpdateRequest salaryUpdateRequest) throws EntityNotFoundException;

    void deleteSalaryById(Long salaryId) throws EntityNotFoundException;
}
