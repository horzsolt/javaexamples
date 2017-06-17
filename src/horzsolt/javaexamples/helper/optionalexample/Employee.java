package horzsolt.javaexamples.helper.optionalexample;

import java.util.Optional;

/**
 * Created by horzsolt on 2017. 06. 17..
 */
public class Employee {

    private Car car = new Car();

    public Optional < Car > getCar() {

        return Optional.ofNullable(car);

    }

}
