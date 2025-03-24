package com.rayan.salarytracker.dto.expense;

import com.rayan.salarytracker.core.enums.BudgetRuleAllocation;

public class ExpenseUpdateRequestDTO {

    private String description;
    private int amount;
    private BudgetRuleAllocation budgetRuleAllocation;
    private String bank;
    private Boolean status;

    public ExpenseUpdateRequestDTO() {
    }

    public ExpenseUpdateRequestDTO(String description, int amount, BudgetRuleAllocation budgetRule, String bank, Boolean status) {
        this.description = description;
        this.amount = amount;
        this.budgetRuleAllocation = budgetRule;
        this.bank = bank;
        this.status = status;
    }

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

    public BudgetRuleAllocation getBudgetRuleAllocation() {
        return budgetRuleAllocation;
    }

    public void setBudgetRuleAllocation(BudgetRuleAllocation budgetRuleAllocation) {
        this.budgetRuleAllocation = budgetRuleAllocation;
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
}
