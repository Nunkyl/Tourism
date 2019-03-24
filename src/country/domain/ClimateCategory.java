package country.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eliza on 17.03.19.
 */
public enum ClimateCategory {

    COLD,
    HOT;


    private static Map<String, ClimateCategory> climateMap;

    static {
        climateMap = new HashMap<>();
        for (ClimateCategory enumItem : ClimateCategory.values()) {
            climateMap.put(enumItem.name(), enumItem);
        }
    }

    public static boolean doesStringBelongToEnumValues(String string) {
        return climateMap.containsKey(string);
    }
}
