package com.rayan.salarytracker.dto.expense;

public class ExpenseUpdateRequestDTO {

    private String description;
    private int amount;
    private String budgetRule;
    private String bank;
    private Boolean status;

    public ExpenseUpdateRequestDTO() {
    }

    public ExpenseUpdateRequestDTO(String description, int amount, String budgetRule, String bank, Boolean status) {
        this.description = description;
        this.amount = amount;
        this.budgetRule = budgetRule;
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
}
