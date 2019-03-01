package country.domain;

import city.domain.City;
import common.business.domain.BaseDomain;
import order.domain.Order;

import java.util.List;

/**
 * Created by eliza on 22.02.19.
 */
public class Country extends BaseDomain {

    private String name;
    private String languages;
    private List<City> cities = null;
    private List<Order> orders = null;

    public Country() {
    }

    public Country(String name, String languages) {
        this.name = name;
        this.languages = languages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguages(String language) {
        this.languages = language;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public String getLanguages() {
        return languages;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Order> getOrders() {
        return orders;
    }


    /**
    private String languagesToString(){
        StringBuilder output = new StringBuilder();
        for (String language : languages) {
            output.append(language).append("\n");
        }
        return output.toString();
    }
     **/

    private String citiesToString(){
        StringBuilder output = new StringBuilder();
        if (cities != null) {
            for (City city : cities) {
                output.append(city.getName()).append("\n");
            }
        }
        return output.toString();
    }

    private String ordersToString(){
        StringBuilder output = new StringBuilder();
        if (orders != null) {
            for (Order order : orders) {
                output.append(order.getID().toString()).append("\n"); // This needs to get fixed later
            }
        } else return "";
        return output.toString();
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", languages=" + languages +
                ", citiesInStorage=" + citiesToString() +
                ", ordersInStorage=" + ordersToString() +
                '}';
    }
}
