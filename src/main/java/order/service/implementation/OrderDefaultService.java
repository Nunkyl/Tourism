package order.service.implementation;

import order.domain.Order;
import order.repo.OrderRepo;
import order.search.OrderSearchCondition;
import order.service.OrderService;
import storage.SimpleSequenceGenerator;

import java.util.Collection;
import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public class OrderDefaultService implements OrderService {

    private final OrderRepo orderRepo;

    public OrderDefaultService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public Order add(Order order) {
        if (order != null) {
            order.setID(SimpleSequenceGenerator.getNextID());
            orderRepo.add(order);
        }
        return order;
    }

    @Override
    public Order findByID(Integer id) {
        if (id != null) {
            return orderRepo.findByID(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Order order) {
        if (order.getID() != null) {
            this.deleteByID(order.getID());
        }
    }

    @Override
    public void add(Collection<Order> orders) {
        if (orders != null) {
            for (Order order: orders) {
                order.setID(SimpleSequenceGenerator.getNextID());
            }
            orderRepo.add(orders);
        }
    }

    @Override
    public void deleteByID(Integer id) {
        if (id != null) {
            orderRepo.deleteByID(id);
        }
    }

    @Override
    public void printAll() {
        orderRepo.printAll();
    }

    @Override
    public List<? extends Order> search(OrderSearchCondition searchCondition) {
        return orderRepo.search(searchCondition);
    }

    @Override
    public void update(Order order) {
        orderRepo.update(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }
}
