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

    int getNumberOfOrdersForCity(Integer cityID);

    int getNumberOfOrdersForCountry(Integer countryID);

    void deleteByUserId(Integer userID);

    List<Order> findByUserId(Integer userID);

    //void deleteByIdTx(Integer ID, Connection connection);

    //Order insertTx(Order order, Connection connection);
}
