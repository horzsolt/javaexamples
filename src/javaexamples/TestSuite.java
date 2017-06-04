package horzsolt.javaexamples;

import horzsolt.javaexamples.test.*;
import horzsolt.javaexamples.test.venkat.ComparatorSample;
import horzsolt.javaexamples.test.venkat.PrimeSample;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

public class TestSuite {


    @RunWith(Suite.class)
    @Suite.SuiteClasses({
            LongAdderExample.class,
            LongAccumulatorExample.class,
            ComparatorSample.class,
            PrimeSample.class,
            LastElementOfAStream.class,
            MapFlatMap.class,
            ReduceExamples.class
    })

    public class FeatureTestSuite {
        // the class remains empty,
        // used only as a holder for the above annotations
    }
}
