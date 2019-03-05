package order.repo.implementation;

import city.domain.City;
import country.domain.Country;
import order.domain.Order;
import order.repo.OrderRepo;
import order.search.OrderSearchCondition;

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
                        for (Country country: order.getCountries()){
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
        return answer; // Check this
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
