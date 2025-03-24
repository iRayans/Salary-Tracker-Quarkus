package com.rayan.salarytracker.model;

import com.rayan.salarytracker.core.enums.BudgetRuleAllocation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Description must not be empty")
    @Column(name = "description")
    private String description;
    @Column(name = "amount")
    private int amount;
    // Not needed for now
//    @Column(name = "category")
//    private String category;
    @Enumerated(EnumType.STRING)
    private BudgetRuleAllocation budgetRuleAllocation;
    @Column(name = "bank")
    private String bank;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    ///////////////////////////////
    // Relations start from here //
    /// ////////////////////////////

    // Many Expenses mapped to a single Salary.
    // The fetch Type set to lazy since we don't need to fetch user object with the response.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "salary_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Salary salary;

    public Expense() {
    }

    public Expense(Long id, Integer amount, Boolean status, Timestamp createdAt, Timestamp updatedAt, String bank, String description, BudgetRuleAllocation budgetRuleAllocation, Salary salary) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.bank = bank;
        this.description = description;
        this.budgetRuleAllocation = budgetRuleAllocation;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public com.rayan.salarytracker.model.Salary getSalary() {
        return salary;
    }

    public void setSalary(com.rayan.salarytracker.model.Salary salary) {
        this.salary = salary;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BudgetRuleAllocation getBudgetRuleAllocation() {
        return budgetRuleAllocation;
    }

    public void setBudgetRuleAllocation(BudgetRuleAllocation budgetRuleAllocation) {
        this.budgetRuleAllocation = budgetRuleAllocation;
    }
}