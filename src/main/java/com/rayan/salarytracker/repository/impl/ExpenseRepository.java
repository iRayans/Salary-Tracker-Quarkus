package com.rayan.salarytracker.repository.impl;

import com.rayan.salarytracker.model.Expense;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ExpenseRepository implements PanacheRepository<Expense> {

    public List<Expense> getExpensesBySalaryId(Long salaryId, Long userId) {
        return find("salary.id = ?1 AND salary.user.id = ?2", salaryId, userId).list();
    }


    public Optional<Expense> getExpenseBySalaryId(Long salaryId, Long expenseId, Long userId) {
        return find("""
                    FROM Expense e
                    JOIN FETCH e.salary s
                    WHERE e.id = ?1 AND s.id = ?2 AND s.user.id = ?3
                """, expenseId, salaryId, userId).firstResultOptional();
    }
}

