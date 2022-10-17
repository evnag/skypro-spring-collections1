package ru.skypro.skyprospringcollections1.service;

import org.springframework.stereotype.Service;
import ru.skypro.skyprospringcollections1.domain.Employee;
import ru.skypro.skyprospringcollections1.exception.EmployeeAlreadyAddedException;
import ru.skypro.skyprospringcollections1.exception.EmployeeNotFoundException;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees;
    private final ValidatorService validatorService;

    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
        this.employees = new HashMap<>();
    }

    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Employee is Already Added");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee addEmployee(String firstName, String lastName, Integer department, int salary) {
        Employee employee = new Employee(
                validatorService.validate(firstName),
                validatorService.validate(lastName),
                department,
                salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Employee is Already Added");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Employee Not Found");
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Employee Not Found");
    }

    public Collection<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }
}
