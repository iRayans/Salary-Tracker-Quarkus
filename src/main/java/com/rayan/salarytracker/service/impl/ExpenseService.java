package com.rayan.salarytracker.service.impl;

import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.expense.ExpenseInsertDTO;
import com.rayan.salarytracker.dto.expense.ExpenseReadOnlyDTO;
import com.rayan.salarytracker.dto.expense.ExpenseUpdateRequestDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.Expense;
import com.rayan.salarytracker.model.Salary;
import com.rayan.salarytracker.repository.impl.ExpenseRepository;
import com.rayan.salarytracker.repository.impl.SalaryRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class ExpenseService {

    @Inject
    ExpenseRepository expenseRepository;
    @Inject
    SalaryRepository salaryRepository;
    @Inject
    Mapper mapper;
    @Inject
    LoggedInUser loggedInUser;

    public List<ExpenseReadOnlyDTO> getAllExpenses(Long salaryId) {
        Long userId = loggedInUser.getUserId();
        List<Expense> expenseList = expenseRepository.getExpensesBySalaryId(salaryId, userId);

        return expenseList.stream().map(mapper::mapToExpenseReadOnlyDTO).toList();
    }

    public ExpenseReadOnlyDTO createExpense(ExpenseInsertDTO expenseInsertDTO, Long salaryId) throws EntityNotFoundException {
        Salary salary = salaryRepository.findById(salaryId);
        if (salary == null) {
            throw new EntityNotFoundException("Salary", "Salary with id: " + salaryId + " not found.");
        }
        Expense expense = mapper.mapToExpense(expenseInsertDTO);
        expense.setSalary(salary);
        expenseRepository.persist(expense);
        return mapper.mapToExpenseReadOnlyDTO(expense);
    }

    // TODO: Prevent user from getting other users expenses.
    public ExpenseReadOnlyDTO getExpenseById(Long expenseId) throws EntityNotFoundException {
        Expense expense = expenseRepository.findById(expenseId);
        if (expense == null) {
            throw new EntityNotFoundException("Expense", "Expense with id: " + expenseId + " not found.");
        }
        return mapper.mapToExpenseReadOnlyDTO(expense);
    }

    public ExpenseReadOnlyDTO updateExpense(ExpenseUpdateRequestDTO expenseUpdateRequest, Long salaryId, Long expenseId) throws EntityNotFoundException {
        return null;
    }
}
