package com.rayan.salarytracker.dto.salary;

import com.rayan.salarytracker.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;



public class SalaryInsertDTO {

    public SalaryInsertDTO(String month, String description, int amount, int year, User user) {
        this.month = month;
        this.description = description;
        this.amount = amount;
        this.year = year;
        this.user = user;
    }

    public SalaryInsertDTO(String month) {
        this.month = month;
    }

    @NotBlank(message = "Month must not be empty")
    private String month;

    @NotBlank(message = "Description must not be empty")
    private String description;

    @PositiveOrZero(message = "Value must be positive or zero")
    private int amount;

    private int year;
    private User user;


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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
