package com.example.Employeeproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Employeeproject.entity.Employee;



@Repository
public interface EmpRepo  extends JpaRepository<Employee, Integer>{

}