package order.service;

import common.business.service.BaseService;
import order.domain.Order;
import order.search.OrderSearchCondition;

import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public interface OrderService extends BaseService {

    void add(Order order);

    Order findByID(Integer ID);

    void delete(Order order);

    List<Order> search (OrderSearchCondition searchCondition);
}
