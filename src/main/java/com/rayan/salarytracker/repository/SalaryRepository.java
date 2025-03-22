package com.rayan.salarytracker.repository;

import com.rayan.salarytracker.model.Salary;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class SalaryRepository implements PanacheRepository<Salary> , ISalaryRepository{

    @Override
    public List<Salary> findSalaryByUserId(Long userId,int year) {
        if(year < 0 ){
            year = 2025;
        }
        return find("year = ?1 AND user.id = ?2", year, userId).list();
    }
}
