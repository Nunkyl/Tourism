package user.domain;

import common.business.domain.BaseDomain;
import order.domain.Order;
import java.util.List;

/**
 * Created by eliza on 22.02.19.
 */
public abstract class BaseUser extends BaseDomain {

    protected String firstName;
    protected String lastName;
    protected List<Order> orders = null;

    public BaseUser() {
    }

    public BaseUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public abstract String toString();

    protected String ordersToString(){
        StringBuilder output = new StringBuilder();
        if (orders != null) {
            for (Order order : orders) {
                output.append(order.getID().toString()).append(" ");
            }
        } else return "";
        return output.toString();
    }
}
