package com.rayan.salarytracker.repository.impl;

import com.rayan.salarytracker.model.Salary;
import com.rayan.salarytracker.repository.ISalaryRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class SalaryRepository implements PanacheRepository<Salary>, ISalaryRepository {

    @Override
    public List<Salary> findSalaryByUserId(Long userId, int year) {
        if (year < 0) {
            year = 2025;
        }
        return find("year = ?1 AND user.id = ?2", year, userId).list();
    }

    /*
     * Checks if a salary already exists for the given user, month, and year.
     * Used to prevent duplicate salary entries for the same period.
     */
    @Override
    public Boolean salaryExistsForUserInMonthAndYear(Long userId, String month, int year) {
        return count("user.id = ?1 AND month =?2 AND year = ?3", userId, month, year) > 0;
    }

}
