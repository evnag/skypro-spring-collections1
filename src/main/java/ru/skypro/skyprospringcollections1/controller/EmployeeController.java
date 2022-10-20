package ru.skypro.skyprospringcollections1.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.skyprospringcollections1.domain.Employee;
import ru.skypro.skyprospringcollections1.exception.EmployeeAlreadyAddedException;
import ru.skypro.skyprospringcollections1.exception.EmployeeNotFoundException;
import ru.skypro.skyprospringcollections1.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName) {
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping("/add-by-department")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam Integer department,
                        @RequestParam Integer salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName,
                           @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName,
                         @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @ExceptionHandler
    public String handleEmployeeNotFoundException(EmployeeNotFoundException e) {
        return "Employee not found.";
    }

    @ExceptionHandler
    public String handleEmployeeNotFoundException(EmployeeAlreadyAddedException e) {
        return "Employee is Already Added";
    }
}
