package com.rayan.salarytracker.service;

import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.salary.SalaryInsertDTO;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;

import java.util.List;

public interface IUserService {

   List<SalaryReadOnlyDTO> findAllSalaries() throws EntityNotFoundException;
   SalaryReadOnlyDTO createSalary(SalaryInsertDTO salaryInsertDTO) throws EntityInvalidArgumentsException, EntityNotFoundException;

}
