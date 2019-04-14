package common.solutions.utils;

/**
 * Created by eliza on 26.02.19.
 */

public final class StringUtils {

    private StringUtils() {

    }

    public static boolean isBlank(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }
}

