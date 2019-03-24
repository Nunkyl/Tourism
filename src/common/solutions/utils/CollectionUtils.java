package common.solutions.utils;

/**
 * Created by eliza on 23.03.19.
 */

import java.util.List;

public final class CollectionUtils {

    public static <T> T getLast(List<T> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(list.size() - 1);
        }
    }
}

