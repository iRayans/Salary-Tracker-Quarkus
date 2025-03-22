package com.rayan.salarytracker.dto.salary;

public class SalaryUpdateRequest {
    private String description;
    private int amount;


    public SalaryUpdateRequest(String description, int amount ) {
        this.description = description;
        this.amount = amount;
    }

    public SalaryUpdateRequest() {
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

    @Override
    public String toString() {
        return "SalaryUpdateRequest{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
