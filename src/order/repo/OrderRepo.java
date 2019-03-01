package order.repo;

import common.business.repo.BaseRepo;
import order.domain.Order;
import order.search.OrderSearchCondition;

import java.util.List;

/**
 * Created by eliza on 22.02.19.
 */
public interface OrderRepo extends BaseRepo {

    void add(Order ordeer);

    Order findByID(Integer ID);

    List<Order> search (OrderSearchCondition searchCondition);
}
