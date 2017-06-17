package horzsolt.javaexamples.helper.optionalexample;

import java.util.Optional;

/**
 * Created by horzsolt on 2017. 06. 17..
 */
public class Car {

    private Insurance insurance = new Insurance();

    public Optional < Insurance > getInsurance() {

        return Optional.ofNullable(insurance);

    }

}
