package com.example.employee_accounting;

import com.example.employee_accounting.exception.EmployeeAlreadyAddedException;
import com.example.employee_accounting.exception.EmployeeNotFoundException;
import com.example.employee_accounting.exception.EmployeeStorageIsFullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees;
    private static final int MAX_EMPLOYEES = 100;

    public EmployeeServiceImpl() {
        employees = new ArrayList<>();

    }

    @Override
    public void addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + firstName + " " + lastName + " уже добавлен");
        }

        if (employees.size() < MAX_EMPLOYEES) {
            employees.add(newEmployee);
        } else {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное количество сотрудников в фирме");
        }
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                iterator.remove();
                System.out.println("Сотрудник " + firstName + " " + lastName + "сотрудник удален.");
                return;
            }
        }
        System.out.println("Сотрудник " + firstName + " " + lastName + "сотрудник не найден.");
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee.getFirstName() + " " + employee.getLastName() + "сотрудник найден";
            }

        }
        throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден.");

    }

}