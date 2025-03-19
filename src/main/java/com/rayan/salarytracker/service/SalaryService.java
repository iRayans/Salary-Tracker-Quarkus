package com.rayan.salarytracker.service;


import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.Salary;
import com.rayan.salarytracker.repository.SalaryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SalaryService {

    @Inject
    SalaryRepository salaryRepository;
   @Inject
    Mapper mapper;

    public List<SalaryReadOnlyDTO> findById(Long id) throws EntityNotFoundException {
        List<Salary> salaries = salaryRepository.findSalaryByUserId(id, 2025);
        List<SalaryReadOnlyDTO> salaryReadOnlyDTO = salaries.stream().map(mapper::mapToSalaryReadOnlyDTO).toList();
        if (salaries == null || salaries.isEmpty()) {
            throw new EntityNotFoundException("Salary", "Salary not found");
        }

        return salaryReadOnlyDTO;
    }
}
