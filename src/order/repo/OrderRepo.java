package order.repo;

import common.solutions.repo.BaseRepo;
import order.domain.Order;
import order.search.OrderSearchCondition;

import java.util.List;

/**
 * Created by eliza on 22.02.19.
 */
public interface OrderRepo extends BaseRepo<Order, Integer> {

    List<? extends Order> search(OrderSearchCondition searchCondition);
}
