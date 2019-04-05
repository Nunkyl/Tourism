package city.repo.implementation;

import city.domain.City;
import city.search.CitySortField;

import java.util.*;

import static city.search.CitySortField.*;
import static common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static java.lang.Integer.compare;

/**
 * Created by eliza on 31.03.19.
 */
public final class CityComparatorComponent {

    private static final CityComparatorComponent INSTANCE = new CityComparatorComponent();
    private static Map<CitySortField, Comparator<City>> comparatorsByField = new HashMap<>();
    private static Set<CitySortField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, COUNTRY_NAME, POPULATION));

    static {
        comparatorsByField.put(NAME, getComparatorForUserField());
        comparatorsByField.put(COUNTRY_NAME, getComparatorForCountryField());
        comparatorsByField.put(POPULATION, getComparatorForCityField());
    }

    private CityComparatorComponent() {
    }

    public static CityComparatorComponent getInstance() {
        return INSTANCE;
    }

    public Comparator<City> getComparatorForField(CitySortField field) {
        return comparatorsByField.get(field);
    }

    private static Comparator<City> getComparatorForUserField() {
        return (city1, city2) -> getComparatorForNullableStrings().compare(city1.getName(), city2.getName());
    }

    private static Comparator<City> getComparatorForCountryField() {
        return (city1, city2) -> getComparatorForNullableStrings().compare(city1.getCountry().getName(),
                city2.getCountry().getName());

    }

    private static Comparator<City> getComparatorForCityField() {
        return (city1, city2) -> {
            if (city1 != null && city2 != null)
                return compare(city1.getPopulation(), city2.getPopulation());
            else return 0;
        };
        //return (city1, city2) -> Comparator.comparingInt(city -> city.getPopulation()); // ?????????????
    }

    public Comparator<City> getComplexComparator(final CitySortField sortField) { // What's up with the final modifier?
        return (city1, city2) -> {
                int result = 0;
                Comparator<City> orderComparator = comparatorsByField.get(sortField);

                if (orderComparator != null) {
                    result = orderComparator.compare(city1, city2);
                    //If o1 and o2 have the same order priority they will then be sorted by all other fields
                    if (result == 0) {
                        for (CitySortField otherField : fieldComparePriorityOrder) {
                            //If the records haven't already been sorted by the current field they are sorted according to it
                            if (!otherField.equals(sortField)) {
                                result = comparatorsByField.get(otherField).compare(city1, city2);
                                //If the records are not equal we can exit the loop
                                if (result != 0) {
                                    break;
                                }
                            }
                        }

                    }
                }
                return result;
        };
    }
}
