package country.repo.implementation;

import country.domain.BaseCountry;
import country.search.CountrySearchCondition;
import country.search.CountrySortField;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static common.business.search.SortDirection.*;
import static common.business.search.SortType.*;
import static java.util.Collections.reverseOrder;


/**
 * Created by eliza on 26.03.19.
 */
public final class CountrySortingComponent {

    public void applyOrdering(List<? extends BaseCountry> listOfCountries, CountrySearchCondition countrySearchCondition) {
        Comparator<BaseCountry> countryComparator = null;

        CountrySortField field = countrySearchCondition.getSortField();

        switch (countrySearchCondition.getSortType()) {
            case SIMPLE: {
                countryComparator = CountryComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                countryComparator = CountryComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (countryComparator != null) {
            switch (countrySearchCondition.getSortDirection()) {
                case ASC:
                    Collections.sort(listOfCountries, countryComparator);
                    break;
                case DECS:
                    Collections.sort(listOfCountries, reverseOrder(countryComparator));
                    break;
            }
        }
    }
}
