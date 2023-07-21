package com.example.employee_accounting;

import com.example.employee_accounting.exception.EmployeeAlreadyAddedException;
import com.example.employee_accounting.exception.EmployeeNotFoundException;
import com.example.employee_accounting.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees;
    private static final int MAX_EMPLOYEES = 100;

    public EmployeeServiceImpl() {
        employees = new ArrayList<>();

    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.size() < MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("превышен лимит количества сотрудников в фирме");
        }

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("в коллекции уже есть такой сотрудник");
        }
        employees.add(newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("сотрудник не найден");

    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("сотрудник не найден");

    }

    @Override
    public Collection<Employee> printAll() {
        return Collections.unmodifiableCollection(employees);
    }

}