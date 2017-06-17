package horzsolt.javaexamples.test;

import horzsolt.javaexamples.helper.optionalexample.Car;
import horzsolt.javaexamples.helper.optionalexample.Employee;
import horzsolt.javaexamples.helper.optionalexample.Insurance;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

/**
 * Created by horzsolt on 2017. 06. 17..
 */
public class OptionalExample {

    @Test
    public void getInsuranceName(Optional<Employee> employee) {

        Employee emp = new Employee();

        assertTrue(employee.flatMap(Employee::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("UNKNOWN") != null);
    }
}
