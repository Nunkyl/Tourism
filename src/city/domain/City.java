package city.domain;

import common.business.domain.BaseDomain;
import country.domain.BaseCountry;
import order.domain.Order;

import java.util.List;

/**
 * Created by eliza on 22.02.19.
 */
public class City extends BaseDomain{

    private String name;
    private Integer population;
    private boolean isCapital;
    private BaseCountry country;
    private List<Order> orders = null;

    public City() {
    }

    public City(String name, int population, boolean isCapital, BaseCountry country) {
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public void setCountry(BaseCountry country) {
        this.country = country;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public BaseCountry getCountry() {
        return country;
    }

    public List<Order> getOrders() {
        return orders;
    }

    private String ordersToString(){
        StringBuilder output = new StringBuilder();
        if (orders != null) {
            for (Order order : orders) {
                output.append(order.getID().toString()).append(" ");
            }
        } else return "";
        return output.toString();
    }

    @Override
    public String toString() {
        return "City{" +
                "ID=" + ID + '\'' +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", isCapital=" + isCapital +
                ", country=" + country.getName() +
                ", ordersInStorage=" + ordersToString() +
                '}';
    }
}
