package com.rayan.salarytracker.mapper;


import com.rayan.salarytracker.core.enums.BudgetRuleAllocation;
import com.rayan.salarytracker.core.enums.RoleType;
import com.rayan.salarytracker.dto.expense.ExpenseInsertDTO;
import com.rayan.salarytracker.dto.expense.ExpenseReadOnlyDTO;
import com.rayan.salarytracker.dto.salary.SalaryInsertDTO;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;
import com.rayan.salarytracker.dto.user.UserInsertDTO;
import com.rayan.salarytracker.dto.user.UserReadOnlyDTO;
import com.rayan.salarytracker.model.Expense;
import com.rayan.salarytracker.model.Salary;


import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.security.PasswordUtil;
import jakarta.enterprise.context.ApplicationScoped;

import static com.rayan.salarytracker.core.enums.BudgetRuleAllocation.*;


@ApplicationScoped
public class Mapper {
    public Mapper() {
    }

    // ======================================
    // =          User Mapper               =
    // ======================================

    public User mapToUser(UserInsertDTO userInsertDTO) {
        return new User(null, userInsertDTO.getUsername(), userInsertDTO.getEmail(), PasswordUtil.encryptPassword(userInsertDTO.getPassword()), RoleType.valueOf(userInsertDTO.getRole()), null, null);
    }

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole().name(), user.getCreatedAt(), user.getUpdatedAt());
    }

    // ======================================
    // =          Salary Mapper             =
    // ======================================

    public Salary mapToSalary(SalaryInsertDTO salaryInsertDTO) {
        return new Salary(null,
                salaryInsertDTO.getMonth(),
                0,
                salaryInsertDTO.getDescription(),
                salaryInsertDTO.getAmount(),
                null,
                null,
                null);
    }

    public SalaryReadOnlyDTO mapToSalaryReadOnlyDTO(Salary salary) {
        return new SalaryReadOnlyDTO(salary.getId(),
                salary.getMonth(),
                salary.getDescription(),
                salary.getAmount(),
                salary.getYear(),
                salary.getCreatedAt(),
                salary.getUpdatedAt());
    }


    // ======================================
    // =          Expense Mapper            =
    // ======================================
    public Expense mapToExpense(ExpenseInsertDTO expenseInsertDTO) {
        return new Expense(
                null,
                expenseInsertDTO.getAmount(),
                expenseInsertDTO.getStatus(),
                null,
                null,
                expenseInsertDTO.getBank(),
                expenseInsertDTO.getDescription(),
                valueOf(expenseInsertDTO.getBudgetRule()),
                expenseInsertDTO.getSalary()
        );
    }

    public ExpenseReadOnlyDTO mapToExpenseReadOnlyDTO(Expense expense) {
        return new ExpenseReadOnlyDTO(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getBudgetRuleAllocation().name(),
                expense.getStatus(),
                expense.getBank(),
                expense.getCreatedAt(),
                expense.getUpdatedAt()
        );
    }
//
//    // ======================================
//    // =          Summary Mapper            =
//    // ======================================
//
//    public Summary mapToSummary(SummaryInsertDTO summaryInsertDTO) {
//        return new Summary(null, summaryInsertDTO.getTotalExpense(), summaryInsertDTO.getRemainingSalary(), null, null, summaryInsertDTO.getSalary());
//    }
//
//    public SummaryReadOnlyDTO mapToSummaryReadOnlyDTO(Summary summary) {
//        return new SummaryReadOnlyDTO(summary.getId(),
//                summary.getTotalExpense(),
//                summary.getRemainingSalary(),
//                summary.getSalary().getMonth(),
//                summary.getSalary().getAmount(),
//                summary.getUpdatedAt(),
//                summary.getCreatedAt());
//    }

}
