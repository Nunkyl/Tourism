package order.repo.implementation;

import city.domain.City;
import common.business.search.Paginator;
import common.solutions.utils.CollectionUtils;
import country.domain.BaseCountry;
import order.domain.Order;
import order.repo.OrderRepo;
import order.search.OrderSearchCondition;

import java.util.*;

import static common.solutions.utils.CollectionUtils.getPageableData;
import static storage.Storage.ordersInStorage;

/**
 * Created by eliza on 26.02.19.
 */
public class OrderMemoryListRepo implements OrderRepo {

    private OrderSortingComponent orderingComponent = new OrderSortingComponent();

    @Override
    public void printAll() {
        for (Order order : ordersInStorage) {
            if (order != null) {
                System.out.println(order);
            }
        }
    }

    @Override
    public void deleteByID(Integer id) {
        Integer orderIndex = findOrderIndexById(id);

        if (orderIndex != null) {
            deleteOrderByIndex(orderIndex);
        }
    }

    @Override
    public void add(Order order) {
        ordersInStorage.add(order);
    }

    @Override
    public void add(Collection<Order> orders) {
        ordersInStorage.addAll(orders);
    }

    @Override
    public Order findByID(Integer id) {
        Integer orderIndex = findOrderIndexById(id);
        if (orderIndex != null) {
            return ordersInStorage.get(orderIndex);
        }
        return null;
    }

    @Override
    public List<? extends Order> search(OrderSearchCondition searchCondition) {
        if (searchCondition.getID() != null) {
            return Collections.singletonList(findByID(searchCondition.getID())); // returns an immutable list containing only the specified object
        } else {
            List<? extends Order> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needSorting();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }

            if (!result.isEmpty() && searchCondition.needPaginator()) {
                result = getPageableData(result, searchCondition.getPaginator());
            }
            return result;
        }
    }

    private List<? extends Order> getPageableData(List<? extends Order> models, Paginator paginator) {
        return CollectionUtils.getPageableData(models, paginator.getLimit(), paginator.getOffset());
    }

    private List<Order> doSearch(OrderSearchCondition searchCondition) {
        List<Order> result = new LinkedList<>();
        for (Order order : ordersInStorage) { // from Storage
            if (order != null) {
                boolean found = true;

                if (searchCondition.searchByCountry()) {
                    for (BaseCountry country: order.getCountries()){
                        if (searchCondition.getCountry().equals(country.getName())){
                            found = true;
                            break;
                        } else found = false;
                    }
                }

                if (found && searchCondition.searchByCity()) {
                    for (City city: order.getCities()){
                        if (searchCondition.getCity().equals(city.getName())){
                            found = true;
                            break;
                        } else found = false;
                    }
                }

                if (found && searchCondition.searchByUser()) {
                    found = searchCondition.getUser().equals(order.getUser().getLastName() + " "
                            + order.getUser().getFirstName());
                }

                if (found) {
                    result.add(order);
                }
            }
        }
        return result;
    }

    /*
    Old version of search
    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        List<Order> answer = new LinkedList<>();

        if (searchCondition.getID() != null) {
            answer.add(this.findByID(searchCondition.getID()));
            return answer;
        } else {
            boolean searchByCountry = searchCondition.getCountry() != null;
            boolean searchByCity = searchCondition.getCity() != null;
            boolean searchByUser = searchCondition.getUser() != null;

            for (Order order : ordersInStorage) {
                if (order != null) {
                    boolean found = false;
                    boolean buf = false;

                    if (searchByUser) {
                        found = searchCondition.getUser().equals(order.getUser());
                    }

                    if (searchByCountry) {
                        for (BaseCountry country: order.getCountries()){
                            if (searchCondition.getCountry().equals(country)) buf = true;
                        }
                        found = found && buf;
                        buf = false;
                    }

                    if (searchByCity) {
                        for (City city: order.getCities()){
                            if (searchCondition.getCity().equals(city)) buf = true;
                        }
                        found = found && buf;
                    }

                    if (found) {
                        answer.add(order);
                    }
                }
            }
        }

        if (searchCondition.getSortType() == SortDirection.ASC)
            Collections.sort(answer, new compareOrder());

        if (searchCondition.getSortType() == SortDirection.DECS)
            Collections.sort(answer, Collections.reverseOrder(new compareOrder()));

        return answer; // Check this
    }


    private class compareOrder implements Comparator<Order> {

        @Override
        public int compare(Order o1, Order o2) {

            //if (o1.getID().compareTo(o2.getID()) == 0){

                if (o1.getUser().getLastName().compareTo(o2.getUser().getLastName()) == 0){

                    return o1.getUser().getFirstName().compareTo(o2.getUser().getFirstName());

                } else return o1.getUser().getLastName().compareTo(o2.getUser().getLastName());

            //} else return o1.getID().compareTo(o2.getID());
        }
    }

    */

    private void deleteOrderByIndex(int index) {
        ordersInStorage.remove(index);
    }

    private Integer findOrderIndexById(Integer orderId) {
        int index = 0;
        for (Order order: ordersInStorage) {
            if (orderId.equals(order.getID())) {
                return index;
            } else index++;
        }
        return null;
    }

    @Override
    public void update(Order order) {
        // Fill in later
    }

    @Override
    public List<Order> findAll() {
        return ordersInStorage;
    }
}
