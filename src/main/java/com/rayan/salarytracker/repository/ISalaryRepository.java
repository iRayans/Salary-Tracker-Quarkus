package com.rayan.salarytracker.repository;

import com.rayan.salarytracker.model.Salary;

import java.util.List;

public interface ISalaryRepository {
    List<Salary> findSalaryByUserId(Long userId, int year);
    Boolean salaryExistsForUserInMonthAndYear(Long userId, String month, int year);
}
