package country.repo.implementation;

import country.domain.BaseCountry;
import country.search.CountrySortField;

import java.util.*;

import static common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static country.search.CountrySortField.*;

/**
 * Created by eliza on 26.03.19.
 */
public final class CountryComparatorComponent {

    private CountryComparatorComponent() {
    }

    private static final CountryComparatorComponent INSTANCE = new CountryComparatorComponent();
    private static Map<CountrySortField, Comparator<BaseCountry>> comparatorsByField = new HashMap<>();
    private static Set<CountrySortField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, CITY));

    static {
        comparatorsByField.put(NAME, getComparatorForNameField());
        comparatorsByField.put(CITY, getComparatorForCityField());
    }

    public static CountryComparatorComponent getInstance() {
        return INSTANCE;
    }

    public Comparator<BaseCountry> getComparatorForField(CountrySortField field) {
        return comparatorsByField.get(field);
    }

    private static Comparator<BaseCountry> getComparatorForNameField() {
        return (country1, country2) -> getComparatorForNullableStrings().compare(country1.getName(), country2.getName());
    }

    private static Comparator<BaseCountry> getComparatorForCityField() {
        return (country1, country2) -> getComparatorForNullableStrings().compare(country1.getCities().get(0).getName(),
                country2.getCities().get(0).getName());
    }

    public Comparator<BaseCountry> getComplexComparator(final CountrySortField sortField) { // What's up with the final modifier?
        return new Comparator<BaseCountry>() {

            @Override
            public int compare(BaseCountry c1, BaseCountry c2) {
                int result = 0;
                Comparator<BaseCountry> countryComparator = comparatorsByField.get(sortField);

                if (countryComparator != null) {
                    result = countryComparator.compare(c1, c2);
                    //If o1 and o2 have the same order priority they will then be sorted by all other fields
                    if (result == 0) {
                        for (CountrySortField otherField : fieldComparePriorityOrder) {
                            //If the records haven't already been sorted by the current field they are sorted according to it
                            if (!otherField.equals(sortField)) {
                                result = comparatorsByField.get(otherField).compare(c1, c2);
                                //If the records are not equal we can exit the loop
                                if (result != 0) {
                                    break;
                                }
                            }
                        }

                    }
                }
                return result;
            }
        };
    }
}
