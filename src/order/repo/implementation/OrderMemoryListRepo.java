package order.repo.implementation;

import city.domain.City;
import common.business.search.SortType;
import country.domain.BaseCountry;
import order.domain.Order;
import order.repo.OrderRepo;
import order.search.OrderSearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static storage.Storage.ordersInStorage;

/**
 * Created by eliza on 26.02.19.
 */
public class OrderMemoryListRepo implements OrderRepo {

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
    public Order findByID(Integer id) {
        Integer orderIndex = findOrderIndexById(id);
        if (orderIndex != null) {
            return ordersInStorage.get(orderIndex);
        }
        return null;
    }

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

        if (searchCondition.getSortType() == SortType.ASC)
            Collections.sort(answer, new compareOrder());

        if (searchCondition.getSortType() == SortType.DECS)
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
}
