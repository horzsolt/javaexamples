package horzsolt.javaexamples.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * http://www.baeldung.com/java-difference-map-and-flatmap
 */
public class MapFlatMap {

    @Test
    //The map() method works well with Optional – if the function returns the exact type we need
    public void simpleOptional() {

        Optional<String> s = Optional.of("test");
        assertEquals(Optional.of("TEST"), s.map(String::toUpperCase));
    }

    @Test
    public void nestedOptionals() {

        assertEquals(
                Optional.of(Optional.of("STRING")),
                Optional.of("string").map(s -> Optional.of("STRING")));

        /*
        As we can see, we end up with the nested structure Optional<Optional<String>>.
        Although it works, it’s pretty cumbersome to use and does not provide any additional null-safety, so it is better to keep a flat structure.
        That’s exactly what flatMap() helps us to do:
         */

        assertEquals(
                Optional.of("STRING"),
                Optional.of("string").flatMap(s -> Optional.of("STRING")));
    }

    @Test
    public void usingThemInStreams() {

        /*
        The map() method wraps the underlying sequence in a Stream instance, whereas the flatMap() method allows avoiding nested Stream<Stream<R>> structure.
        In the following example, map() produces a Stream consisting of the results of applying the toUpperCase() method to the elements of the input Stream:
         */
        List<String> myList = Stream.of("a", "b")
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("A", "B"), myList);

        /*
        map() works pretty well in such a simple case, but what if we have something more complex such as a list of lists as an input.
         */
        List<List<String>> list = Arrays.asList(
                Arrays.asList("a"),
                Arrays.asList("b"));
        System.out.println(list); //[[a], [b]]

        //Now, let’s use a flatMap():
        System.out.println(list
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList())); //[a, b]


    }
}
