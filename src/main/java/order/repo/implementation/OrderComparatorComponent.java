package order.repo.implementation;

import order.domain.Order;
import order.search.OrderSortField;

import java.util.*;

import static common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static order.search.OrderSortField.*;

/**
 * Created by EL on 24.03.19.
 */
public final class OrderComparatorComponent {

    private static final OrderComparatorComponent INSTANCE = new OrderComparatorComponent();
    private static Map<OrderSortField, Comparator<Order>> comparatorsByField = new HashMap<>();
    /**
     * For complex comparator only
     */
    private static Set<OrderSortField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(USER, COUNTRY, CITY));

    static {
        comparatorsByField.put(USER, getComparatorForUserField());
        comparatorsByField.put(COUNTRY, getComparatorForCountryField());
        comparatorsByField.put(CITY, getComparatorForCityField());
    }

    private OrderComparatorComponent() {
    }


    public static OrderComparatorComponent getInstance() {
        return INSTANCE;
    }

    private static Comparator<Order> getComparatorForUserField() {
        return new Comparator<Order>() {
            @Override
            public int compare(Order order1, Order order2) {

                if (getComparatorForNullableStrings().compare(order1.getUser().getLastName(),
                        order2.getUser().getLastName()) == 0){
                    return getComparatorForNullableStrings().compare(order1.getUser().getFirstName(),
                            order2.getUser().getFirstName());
                }
                else return getComparatorForNullableStrings().compare(order1.getUser().getLastName(),
                        order2.getUser().getLastName());
            }
        };
    }

    private static Comparator<Order> getComparatorForCountryField() {
        return new Comparator<Order>() {
            @Override
            public int compare(Order order1, Order order2) {
                return getComparatorForNullableStrings().compare(order1.getCountries().get(0).getName(),
                        order2.getCountries().get(0).getName());
            }
        };
    }

    public static Comparator<Order> getComparatorForCityField() {
        return new Comparator<Order>() {
            @Override
            public int compare(Order order1, Order order2) {
                return getComparatorForNullableStrings().compare(order1.getCities().get(0).getName(),
                        order2.getCities().get(0).getName());
            }
        };
    }

    public Comparator<Order> getComparatorForField(OrderSortField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<Order> getComplexComparator(final OrderSortField sortField) { // What's up with the final modifier?
        return new Comparator<Order>() {

            @Override
            public int compare(Order o1, Order o2) {
                int result = 0;
                Comparator<Order> orderComparator = comparatorsByField.get(sortField);

                if (orderComparator != null) {
                    result = orderComparator.compare(o1, o2);
                    //If o1 and o2 have the same order priority they will then be sorted by all other fields
                    if (result == 0) {
                        for (OrderSortField otherField : fieldComparePriorityOrder) {
                            //If the records haven't already been sorted by the current field they are sorted according to it
                            if (!otherField.equals(sortField)) {
                                result = comparatorsByField.get(otherField).compare(o1, o2);
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
