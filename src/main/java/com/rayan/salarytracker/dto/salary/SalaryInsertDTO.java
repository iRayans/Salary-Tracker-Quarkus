package com.rayan.salarytracker.dto.salary;

import com.rayan.salarytracker.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;


public class SalaryInsertDTO {

    public SalaryInsertDTO(String month, String description, int amount) {
        this.month = month;
        this.description = description;
        this.amount = amount;
    }

    public SalaryInsertDTO() {;
    }

    @NotBlank(message = "Month must not be empty")
    private String month;

    @NotBlank(message = "Description must not be empty")
    private String description;

    @PositiveOrZero(message = "Value must be positive or zero")
    private int amount;


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
}
