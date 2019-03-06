package common.solutions.utils.extra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by eliza on 05.03.19.
 */
public class ListsIntersection {

    public static List<String> findIntersection(List<String> list1, List<String> list2){

        Set<String> result = new HashSet<>();

        for (String element : list1){
            if (list2.contains(element))
                result.add(element);

        }

        return new ArrayList<>(result);
    }
}
