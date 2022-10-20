package ru.skypro.skyprospringcollections1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.skypro.skyprospringcollections1.domain.Employee;
import ru.skypro.skyprospringcollections1.exception.EmployeeAlreadyAddedException;
import ru.skypro.skyprospringcollections1.exception.EmployeeNotFoundException;
import ru.skypro.skyprospringcollections1.exception.IncorrectParamException;
import ru.skypro.skyprospringcollections1.service.EmployeeService;
import ru.skypro.skyprospringcollections1.service.ValidatorService;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());

    @AfterEach
    public void afterEach() {
        employeeService.findAll().forEach(employee -> employeeService.removeEmployee(employee.getFirstName(), employee.getLastName()));
    }

    @Test
    public void addEmployeePositiveTest() {
        addEmployeeWithCheck();
    }

    private Employee addEmployeeWithCheck(String firstName, String lastName) {
        Employee expected = new Employee(firstName, lastName, 1, 30_000);
        int sizeBefore = employeeService.findAll().size();
        employeeService.addEmployee(expected.getFirstName(), expected.getLastName(), expected.getDepartment(), expected.getSalary());
        assertThat(employeeService.findAll())
                .isNotEmpty()
                .hasSize(sizeBefore + 1)
                .contains(expected);
        assertThat(employeeService.findEmployee(expected.getFirstName(), expected.getLastName())).isEqualTo(expected);
        return expected;
    }

    private Employee addEmployeeWithCheck() {
        return addEmployeeWithCheck("Name", "Lastname");
    }

    @ParameterizedTest
    @MethodSource("addNegative1Params")
    public void addEmployeeNegative1Test(String firstName,
                                         String lastName,
                                         Class<Throwable> expectedExceptionType) {
        assertThatExceptionOfType(expectedExceptionType)
                .isThrownBy(() -> employeeService.addEmployee(firstName, lastName, 1, 30_000));
    }

    @Test
    public void addEmployeeNegative2Test() {
        Employee employee = addEmployeeWithCheck();
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getDepartment(), employee.getSalary()));
    }

    @Test
    public void addEmployeeNegative3Test() {
        Employee employee = addEmployeeWithCheck();
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee(employee.getFirstName(), employee.getLastName()));
    }

    @Test
    public void findEmployeePositive() {
        Employee employee1 = addEmployeeWithCheck("Name", "Lastname");
        Employee employee2 = addEmployeeWithCheck("Имя", "Фамилия");
        assertThat(employeeService.findEmployee(employee1.getFirstName(), employee1.getLastName()))
                .isEqualTo(employee1);
        assertThat(employeeService.findEmployee(employee2.getFirstName(), employee2.getLastName()))
                .isEqualTo(employee2);

    }

    @Test
    public void findEmployeeNegative() {
    assertThatExceptionOfType(EmployeeNotFoundException.class)
            .isThrownBy(()->employeeService.findEmployee("forTest", "forTest"));
        addEmployeeWithCheck("Name", "Lastname");
        addEmployeeWithCheck("Имя", "Фамилия");
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.findEmployee("forTest", "forTest"));
    }

    @Test
    public void removeEmployeePositive() {
        Employee employee1 = addEmployeeWithCheck("Name", "Lastname");
        Employee employee2 = addEmployeeWithCheck("Имя", "Фамилия");
        employeeService.removeEmployee(employee1.getFirstName(), employee1.getLastName());
        assertThat(employeeService.findAll())
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(employee2);
        employeeService.removeEmployee(employee2.getFirstName(), employee2.getLastName());
        assertThat(employeeService.findAll()).isEmpty();

    }

    @Test
    public void removeEmployeeNegative() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.removeEmployee("forTest", "forTest"));
        addEmployeeWithCheck("Name", "Lastname");
        addEmployeeWithCheck("Имя", "Фамилия");
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->employeeService.removeEmployee("forTest", "forTest"));
    }

    public static Stream<Arguments> addNegative1Params() {
        return Stream.of(
                Arguments.of("Ivan1", "Ivanov", IncorrectParamException.class),
                Arguments.of("Ivan", "Ivanov1", IncorrectParamException.class),
                Arguments.of("Ivan@", "Ivanov", IncorrectParamException.class),
                Arguments.of("Ivan", "Ivanov%", IncorrectParamException.class),
                Arguments.of("Ivan-Ivan", "Ivanov", IncorrectParamException.class),
                Arguments.of("Ivan", "Ivanov-Ivanov1", IncorrectParamException.class),
                Arguments.of("Ivan", "Ivanov-%Sidorov", IncorrectParamException.class)
        );
    }
}
