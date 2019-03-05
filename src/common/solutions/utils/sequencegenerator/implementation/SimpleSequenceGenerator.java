package common.solutions.utils.sequencegenerator.implementation;

import common.solutions.utils.sequencegenerator.SequenceGenerator;

/**
 * Created by eliza on 03.03.19.
 */

// Not thread-safe
public class SimpleSequenceGenerator implements SequenceGenerator {

    private Integer value = 1;

    @Override
    public Integer getNextID() {
        return value++;
    }
}
