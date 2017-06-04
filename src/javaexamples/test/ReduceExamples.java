package horzsolt.javaexamples.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


/**
 * http://www.programcreek.com/2014/01/reduce-stream-examples/
 */
public class ReduceExamples {

    List<String> list = new ArrayList<String>();
    Stream<String> wordStream;

    @Before
    public void createList() {

        list.add("java");
        list.add("php");
        list.add("python");
        list.add("perl");
        list.add("c");
        list.add("lisp");
        list.add("c#");

        wordStream = list.stream();
    }

    @After
    public void disposeList() {
        list.clear();
        wordStream.close();
    }

    @Test
    public void withOptional() {

        Stream<Integer> lengthStream = wordStream.map(s -> s.length());
        Optional<Integer> sum = lengthStream.reduce((x, y) -> x + y);
        sum.ifPresent(System.out::println);
    }

    @Test
    /*
    The initial value must be a value for the reduce function. For all t, reduce(identity, t) and reduce(t) should return the same value, but not in the same type (One is a value, the other is an Optional).
     */
    public void withoutOptional() {

        Stream<Integer> lengthStream = wordStream.map(s -> s.length());
        int sum = lengthStream.reduce(0, (x, y) -> x + y);
        System.out.println(sum);
    }

    @Test
    /*
    The three parameters are identify, reducer, and combiner.
    - identity - identity value for the combiner function
    - reducer - function for combining two results
    - combiner - function for adding an additional element into a result.
     */
    public void compact() {
        int s = wordStream.reduce(0, (x, y) -> x + y.length(), (x, y) -> x + y);
        System.out.println(s);
    }
}
