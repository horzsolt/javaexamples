package horzsolt.javaexamples.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * http://www.baeldung.com/java-stream-last-element
 */
public class LastElementOfAStream {

    List<String> valueList = new ArrayList<>();
    Stream<String> stream;

    @Before
    public void createList() {

        valueList.add("Joe");
        valueList.add("John");
        valueList.add("Sean");

        stream = valueList.stream();
    }

    @After
    public void disposeList() {
        valueList.clear();
        stream.close();
    }

    @Test
    //This method will only return deterministic results for sequential Streams.
    public void usingReduce() {

        stream.reduce((first, second) -> second)
                .orElse(null);
    }

    @Test
    /*
    The other way to get the last element of the stream is by skipping all the elements before it.
    This can be achieved using Skip function of Stream class.
    Keep in mind that in this case, we are consuming the Stream twice so there is some clear performance impact.
     */
    public void usingSkip() {

        long count = valueList.stream().count();
        Stream<String> stream = valueList.stream();

        stream.skip(count - 1).findFirst().get();
    }
}
