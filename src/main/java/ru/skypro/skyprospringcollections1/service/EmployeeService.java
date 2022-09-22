package ru.skypro.skyprospringcollections1.service;

import org.springframework.stereotype.Service;
import ru.skypro.skyprospringcollections1.domain.Employee;
import ru.skypro.skyprospringcollections1.exception.EmployeeAlreadyAddedException;
import ru.skypro.skyprospringcollections1.exception.EmployeeNotFoundException;
import ru.skypro.skyprospringcollections1.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static final int SIZE = 5;
    private final List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Employee is Already Added");
        }
        if (employees.size() >= SIZE) {
            throw new EmployeeStorageIsFullException("Employee Storage Is Full");
        }
        employees.add(employee);
        return  employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Employee Not Found");
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("Employee Not Found");
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }
}
