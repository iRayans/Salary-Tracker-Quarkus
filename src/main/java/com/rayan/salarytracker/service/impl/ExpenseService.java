package com.rayan.salarytracker.service.impl;

import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.core.exception.security.NotAuthorizedException;
import com.rayan.salarytracker.dto.expense.ExpenseInsertDTO;
import com.rayan.salarytracker.dto.expense.ExpenseReadOnlyDTO;
import com.rayan.salarytracker.dto.expense.ExpenseUpdateRequestDTO;
import com.rayan.salarytracker.dto.salary.SalaryUpdateRequestDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.Expense;
import com.rayan.salarytracker.model.Salary;
import com.rayan.salarytracker.repository.impl.ExpenseRepository;
import com.rayan.salarytracker.repository.impl.SalaryRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
@Transactional
public class ExpenseService {
    private static final Logger LOGGER = Logger.getLogger(ExpenseService.class);

    @Inject
    ExpenseRepository expenseRepository;
    @Inject
    SalaryRepository salaryRepository;
    @Inject
    Mapper mapper;
    @Inject
    LoggedInUser loggedInUser;

    public List<ExpenseReadOnlyDTO> getAllExpenses(Long salaryId) {
        LOGGER.info("Retrieving all expenses for salary id: " + salaryId);
        Long userId = loggedInUser.getUserId();
        List<Expense> expenseList = expenseRepository.getExpensesBySalaryId(salaryId, userId);
        LOGGER.info("Retrieved all expenses for salary id: " + salaryId);
        return expenseList.stream().map(mapper::mapToExpenseReadOnlyDTO).toList();
    }

    public ExpenseReadOnlyDTO createExpense(ExpenseInsertDTO expenseInsertDTO, Long salaryId) throws EntityNotFoundException {
        LOGGER.info("Creating new expense for salary id: " + salaryId);
        Salary salary = salaryRepository.findById(salaryId);
        if (salary == null) {
            LOGGER.error("Salary not found for id: " + salaryId);
            throw new EntityNotFoundException("Salary", "Salary with id: " + salaryId + " not found.");
        }
        Expense expense = mapper.mapToExpense(expenseInsertDTO);
        expense.setSalary(salary);
        expenseRepository.persist(expense);
        LOGGER.info("Created new expense for salary id: " + salaryId);
        return mapper.mapToExpenseReadOnlyDTO(expense);
    }

    public ExpenseReadOnlyDTO getExpenseById(Long salaryId,Long expenseId) throws EntityNotFoundException, NotAuthorizedException {
        LOGGER.info("Retrieving expense for id: " + expenseId);
        Expense expense = expenseRepository.getExpenseBySalaryId(salaryId,expenseId,getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Expense", "Expense: " + expenseId + " not found."));
        return mapper.mapToExpenseReadOnlyDTO(expense);
    }

    public ExpenseReadOnlyDTO updateExpense(ExpenseUpdateRequestDTO expenseUpdateRequest, Long expenseId) throws EntityNotFoundException {
        LOGGER.info("Updating expense for id: " + expenseId);
        Expense expense = findExpenseById(expenseId);
        updateFields(expense,expenseUpdateRequest);
        LOGGER.info("Updated expense for id: " + expenseId);
        return mapper.mapToExpenseReadOnlyDTO(expense);
    }


    // ######### Helper methods #########

    private Expense findExpenseById(Long expenseId) throws EntityNotFoundException {
        Expense expense = expenseRepository.findById(expenseId);
        if (expense == null) {
            throw new EntityNotFoundException("Expense", "Expense with id: " + expenseId + " not found.");
        }
        return expense;
    }

    private void updateFields(Expense existing, ExpenseUpdateRequestDTO updated) {
        LOGGER.info("Updating salary fields...");
        existing.setDescription(updated.getDescription() != null ? updated.getDescription() : existing.getDescription());
        existing.setAmount(updated.getAmount() != 0 ? updated.getAmount() : existing.getAmount());
        existing.setBudgetRuleAllocation(updated.getBudgetRuleAllocation() != null ? updated.getBudgetRuleAllocation() : existing.getBudgetRuleAllocation());
        existing.setBank(updated.getBank() != null ? updated.getBank() : existing.getBank());
        existing.setStatus(updated.getStatus() != null ? updated.getStatus() : existing.getStatus());

    }

    private Long getUserId() {
        return loggedInUser.getUserId();
    }
}
