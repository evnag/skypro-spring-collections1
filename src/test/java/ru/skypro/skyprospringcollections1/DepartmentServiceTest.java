package ru.skypro.skyprospringcollections1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.skyprospringcollections1.domain.Employee;
import ru.skypro.skyprospringcollections1.service.DepartmentService;
import ru.skypro.skyprospringcollections1.service.EmployeeService;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        when(employeeService.findAll()).thenReturn(
                Arrays.asList(
                        new Employee("Ivan", "Ivanov", 3, 10000),
                        new Employee("Petr", "Petrov", 3, 12000),
                        new Employee("Sidr", "Sidorov", 4, 30000),
                        new Employee("Vasya", "Vasin", 4, 20000),
                        new Employee("Sergey", "Kozlov", 4, 70000)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getEmployeeWithMaxSalaryByDepartmentParams" )
    public void getEmployeeWithMaxSalaryByDepartmentTest(Integer department, Optional<Employee> expected) {
        assertThat(departmentService.getEmployeeWithMaxSalaryByDepartment(department)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getEmployeeWithMinSalaryByDepartmentParams" )
    public void getEmployeeWithMinSalaryByDepartment(Integer department, Optional<Employee> expected) {
        assertThat(departmentService.getEmployeeWithMinSalaryByDepartment(department)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getAllEmployeesByDepartmentTestParams" )
    public void getAllEmployeesByDepartmentTest(Integer department, List<Employee> expectedList) {
        assertThat(departmentService.getAllEmployeesByDepartment(department))
                .containsExactlyInAnyOrderElementsOf(expectedList);
    }

    @Test
    public void getOrderedListOfEmployeesTest() {
        assertThat(departmentService.getOrderedListOfEmployees()).containsExactlyInAnyOrderElementsOf(
                List.of(
                        new Employee("Ivan", "Ivanov", 3, 10000),
                        new Employee("Petr", "Petrov", 3, 12000),
                        new Employee("Sidr", "Sidorov", 4, 30000),
                        new Employee("Vasya", "Vasin", 4, 20000),
                        new Employee("Sergey", "Kozlov", 4, 70000)
                )
        );
    }

    public static Stream<Arguments> getEmployeeWithMaxSalaryByDepartmentParams() {
        return Stream.of(
                Arguments.of(3, Optional.of(new Employee("Petr", "Petrov", 3, 12000))),
                Arguments.of(4, Optional.of(new Employee("Sergey", "Kozlov", 4, 70000)))
        );
    }

    public static Stream<Arguments> getEmployeeWithMinSalaryByDepartmentParams() {
        return Stream.of(
                Arguments.of(3, Optional.of(new Employee("Ivan", "Ivanov", 3, 10000))),
                Arguments.of(4, Optional.of(new Employee("Vasya", "Vasin", 4, 20000)))
        );
    }

    public static Stream<Arguments> getAllEmployeesByDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, Collections.emptyList()),
                Arguments.of(2, Collections.emptyList()),
                Arguments.of(3,
                        List.of(
                                new Employee("Ivan", "Ivanov", 3, 10000),
                                new Employee("Petr", "Petrov", 3, 12000)
                        )
                ),
                Arguments.of(4,
                        List.of(
                                new Employee("Sidr", "Sidorov", 4, 30000),
                                new Employee("Vasya", "Vasin", 4, 20000),
                                new Employee("Sergey", "Kozlov", 4, 70000)
                        )
                )
        );
    }
}
