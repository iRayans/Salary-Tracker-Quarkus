package com.rayan.salarytracker.dto.expense;


import com.rayan.salarytracker.model.Salary;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

public class ExpenseInsertDTO {

    public ExpenseInsertDTO() {
    }

    public ExpenseInsertDTO(String description, int amount, String budgetRule, String bank, Boolean status, Salary salary) {
        this.description = description;
        this.amount = amount;
        this.budgetRule = budgetRule;
        this.bank = bank;
        this.status = status;
        this.salary = salary;
    }

    @NotBlank(message = "Description must not be empty")
    private String description;

    @PositiveOrZero(message = "Value must be positive or zero")
    private int amount;

    @NotEmpty(message = "Budget Role must not be empty")
    private String budgetRule;
    private String bank;
    private Boolean status = false;
    private Salary salary;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBudgetRule() {
        return budgetRule;
    }

    public void setBudgetRule(String budgetRule) {
        this.budgetRule = budgetRule;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }
}
