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
                validatorService.validateFirstName(firstName),
                validatorService.validateLastName(lastName),
                department,
                salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Employee is Already Added");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = employees.values().stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee.getFullName());
        return employee;
    }

//    public Employee findEmployee(String firstName, String lastName) {
//        Employee employee = new Employee(firstName, lastName);
//        if (employees.containsKey(employee.getFullName())) {
//            return employees.get(employee.getFullName());
//        }
//        throw new EmployeeNotFoundException("Employee Not Found");
//    }

    public Employee findEmployee(String firstName, String lastName) {
        return employees.values().stream()
                .filter(employee -> employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }
}
