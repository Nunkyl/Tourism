package city.repo.implementation;

import city.domain.City;
import city.search.CitySearchCondition;
import city.search.CitySortField;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.reverseOrder;

/**
 * Created by eliza on 31.03.19.
 */
public class CitySortingComponent {

    public void applyOrdering(List<? extends City> listOfCities, CitySearchCondition citySearchCondition) {
        Comparator<City> cityComparator = null;

        CitySortField field = citySearchCondition.getSortField();
        switch (citySearchCondition.getSortType()) {

            case SIMPLE: {
                cityComparator = CityComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                cityComparator = CityComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (cityComparator != null) {
            switch (citySearchCondition.getSortDirection()) {
                case ASC:
                    Collections.sort(listOfCities, cityComparator);
                    break;
                case DECS:
                    Collections.sort(listOfCities, reverseOrder(cityComparator));
                    break;
            }
        }
    }
}
