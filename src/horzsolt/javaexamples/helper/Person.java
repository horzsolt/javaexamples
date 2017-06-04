package horzsolt.javaexamples.helper;

/**
 * Created by horzsolt on 2017. 05. 13..
 */
public class Person {

    private final int age;
    private final String name;

    @Override
    public String toString() {
        return String.format("%s -- %d", name, age);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Person(String theName, int theAge) {
        this.age = theAge;
        this.name = theName;

    }
}
