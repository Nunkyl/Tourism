package order.service;

import common.solutions.service.BaseService;
import order.domain.Order;
import order.search.OrderSearchCondition;

import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public interface OrderService extends BaseService<Order, Integer> {

    List<Order> search (OrderSearchCondition searchCondition);
}
