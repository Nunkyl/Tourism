package common.solutions.sequencegenerator.implementation;

import common.solutions.sequencegenerator.SequenceGenerator;

/**
 * Created by eliza on 03.03.19.
 */

// Not thread-safe
public class SimpleSequenceGenerator implements SequenceGenerator {

    public static Integer value = 1;

    public static Integer getNextID() {
        return value++;
    }
}
