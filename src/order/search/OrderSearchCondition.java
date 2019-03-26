package order.search;

import common.business.search.BaseSearchCondition;

/**
 * Created by eliza on 26.02.19.
 */
public class OrderSearchCondition extends BaseSearchCondition<Integer> {

    private String user;
    private String country;
    private String city;
    private OrderSortField sortField;

    public void setUser(String user) {
        this.user = user;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSortField(OrderSortField sortByField) {
        this. sortField = sortByField;
    }

    public String getUser() {
        return user;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public OrderSortField getSortByField() {
        return  sortField;
    }

    public boolean searchByUser() {
        return user != null;
    }

    public boolean searchByCountry() {
        return country != null;
    }

    public boolean searchByCity() {
        return city != null;
    }

    public boolean needOrdering() {
        return super.needOrdering() &&  sortField != null;
    }
}
