package com.rayan.salarytracker.dto.expense;


import java.sql.Timestamp;

public class ExpenseReadOnlyDTO {
    private Long id;
    private String description;
    private int amount;
    private String budgetRule;
    private Boolean status;
    private String bank;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ExpenseReadOnlyDTO() {
    }

    public ExpenseReadOnlyDTO(Long id, String description, int amount, String budgetRule, Boolean status, String bank, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.budgetRule = budgetRule;
        this.status = status;
        this.bank = bank;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}