package country.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eliza on 01.03.19.
 */
public enum ClimateType {

    POLAR("cold and dry"),
    BOREAL_FOREST("temperatures fall below freezing for 4–6 months a year"),
    MOUNTAIN("wetter and windier than lowland regions"),
    TEMPERATE_FOREST("warm summers and cool winters with year-round rain or snow"),
    MEDITERRANEAN("hot, dry summers and cool, wet winters"),
    DESERT("hot and dry year-round"),
    DRY_GRASSLAND("hot and dry year-round"),
    TROPICAL_GRASSLAND("hot summers, cold winters, and little rainfall"),
    TROPICAL_RAINFOREST("hot all year");

    private String description;

    ClimateType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    private static Map<String, ClimateType> climateMap;

    static {
        climateMap = new HashMap<>();
        for (ClimateType enumItem : ClimateType.values()) {
            climateMap.put(enumItem.name(), enumItem);
        }
    }

    public static boolean doesStringBelongToEnumValues(String string) {
        return climateMap.containsKey(string);
    }

    public static ClimateType getEnumFromEnumName(String enumName) {
        return climateMap.get(enumName);
    }

}
