package ru.skypro.skyprospringcollections1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.skyprospringcollections1.domain.Employee;
import ru.skypro.skyprospringcollections1.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Optional<Employee> getEmployeeWithMaxSalaryByDepartment(@RequestParam Integer department) {
        return departmentService.getEmployeeWithMaxSalaryByDepartment(department);
    }

    @GetMapping("/min-salary")
    public Optional<Employee> getEmployeeWithMinSalaryByDepartment(@RequestParam Integer department) {
        return departmentService.getEmployeeWithMinSalaryByDepartment(department);
    }

    @GetMapping("/all-by-department")
    public List<Employee> getAllEmployeesByDepartment(@RequestParam Integer department) {
        return departmentService.getAllEmployeesByDepartment(department);
    }

    @GetMapping("/all")
    public List<Employee> getOrderedListOfEmployees() {
        return departmentService.getOrderedListOfEmployees();
    }
}
