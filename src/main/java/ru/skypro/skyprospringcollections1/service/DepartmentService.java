package ru.skypro.skyprospringcollections1.service;

import org.springframework.stereotype.Service;
import ru.skypro.skyprospringcollections1.domain.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Optional<Employee> getEmployeeWithMaxSalaryByDepartment(Integer department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .max(Comparator.comparing(Employee::getSalary));
    }

//    public Employee getEmployeeWithMaxSalaryByDepartment(Integer department) {
//        return employeeService.findAll().stream()
//                .filter(employee -> employee.getDepartment().equals(department))
//                .max(Comparator.comparing(employee -> employee.getSalary()))
//                .orElseThrow(EmployeeNotFoundException::new);
//    }

    public Optional<Employee> getEmployeeWithMinSalaryByDepartment(Integer department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .min(Comparator.comparing(Employee::getSalary));
    }

    public List<Employee> getAllEmployeesByDepartment(Integer department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public List<Employee> getOrderedListOfEmployees() {
        return employeeService.findAll().stream()
                .collect(Collectors.toList());
    }

//    public Map<Integer, List<Employee>> getOrderedListOfEmployees() {
//        return employeeService.findAll().stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment));
//    }
}
