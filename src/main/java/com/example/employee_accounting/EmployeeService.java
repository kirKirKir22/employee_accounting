package com.example.employee_accounting;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    void addEmployee(String firstName, String lastName);

    void removeEmployee(String firstName, String lastName);

    String findEmployee(String firstName, String lastName);


}
