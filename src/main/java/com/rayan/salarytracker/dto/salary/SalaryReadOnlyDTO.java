package com.rayan.salarytracker.dto.salary;



import java.sql.Timestamp;



public class SalaryReadOnlyDTO {

    public SalaryReadOnlyDTO() {
    }

    public SalaryReadOnlyDTO(Long id, String month, String description, int amount, int year, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.month = month;
        this.description = description;
        this.amount = amount;
        this.year = year;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Long id;
    private String month;
    private String description;
    private int amount;
    private int year;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "SalaryReadOnlyDTO{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", year=" + year +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
