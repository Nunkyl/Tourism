package order.repo.implementation;

import order.domain.Order;
import order.search.OrderSearchCondition;
import order.search.OrderSortField;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.reverseOrder;

/**
 * Created by EL on 24.03.19.
 */
public class OrderSortingComponent {

    public void applyOrdering(List<Order> listOfOrders, OrderSearchCondition orderSearchCondition) {
        Comparator<Order> orderComparator = null;

        OrderSortField field = orderSearchCondition.getSortByField();
        switch (orderSearchCondition.getSortType()) {

            case SIMPLE: {
                orderComparator = OrderComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                orderComparator = OrderComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (orderComparator != null) {
            switch (orderSearchCondition.getSortDirection()) {
                case ASC:
                    Collections.sort(listOfOrders, orderComparator);
                    break;
                case DECS:
                    Collections.sort(listOfOrders, reverseOrder(orderComparator));
                    break;
            }
        }
    }
}
