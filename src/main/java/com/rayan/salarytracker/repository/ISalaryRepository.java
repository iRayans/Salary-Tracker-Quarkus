package com.rayan.salarytracker.repository;

import com.rayan.salarytracker.model.Salary;

import java.util.List;

public interface ISalaryRepository {
    public List<Salary> findSalaryByUserId(Long userId, int year);
}
