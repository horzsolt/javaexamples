package horzsolt.javaexamples.test.venkat;

import horzsolt.javaexamples.helper.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Created by horzsolt on 2017. 05. 13..
 */
public class ComparatorSample {

    public static List<Person> sortBy(List<Person> people, Comparator<Person> comparator) {
        return people.stream().sorted(comparator).collect(toList());
    }

    public static <T extends Comparable<T>> Comparator<Person> comparing(Function<Person, T> by) {
        return (person1, person2) ->
            by.apply(person1).compareTo(by.apply(person2));
    }

    @Test
    public void runExample() {
        List<Person> people = Arrays.asList(
                new Person("Sara", 12),
                new Person("Mark", 43),
                new Person("Bob", 12),
                new Person("Jill", 64));

        Function<Person, String> byName = Person::getName;

        Comparator<Person> compareByAge = (person1, person2) ->
                new Integer(person1.getAge()).compareTo(new Integer(person2.getAge()));

        Comparator<Person> compareByName = (person1, person2) ->
                byName.apply(person1).compareTo(byName.apply(person2));

        System.out.print(people);
        System.out.print(sortBy(people, compareByAge));
        System.out.print(sortBy(people, compareByName));

        System.out.print(sortBy(people, comparing(Person::getAge)));
        System.out.print(sortBy(people, comparing(Person::getName)));
    }
}
