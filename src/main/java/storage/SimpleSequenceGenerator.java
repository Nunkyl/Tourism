package storage;

/**
 * Created by eliza on 03.03.19.
 */

// Not thread-safe
public final class SimpleSequenceGenerator {

    private static Integer value = 1;

    private SimpleSequenceGenerator() { }

    public static Integer getNextID() {
        return value++;
    }
}
