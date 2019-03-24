package country.domain;

import city.domain.City;
import common.business.domain.BaseDomain;
import order.domain.Order;

import java.util.List;

/**
 * Created by eliza on 22.02.19.
 */
public abstract class BaseCountry extends BaseDomain {

    protected ClimateCategory discriminator = null;

    protected String name;
    protected String languages;
    private List<City> cities = null;
    private List<Order> orders = null;
    private ClimateType climateType;

    public BaseCountry() {
    }

    public BaseCountry(String name, String languages) {
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

    public void setDiscriminator(ClimateCategory discriminator) {
        this.discriminator = discriminator;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setClimateType(ClimateType climateType) {
        this.climateType = climateType;
    }

    public ClimateCategory getDiscriminator() {
        return discriminator;
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

    public ClimateType getClimateType() {
        return climateType;
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

    protected String citiesToString(){
        StringBuilder output = new StringBuilder();
        if (cities != null) {
            for (City city : cities) {
                output.append(city.getName()).append(", ");
            }
        }
        return output.toString();
    }

    protected String ordersToString(){
        StringBuilder output = new StringBuilder();
        if (orders != null) {
            for (Order order : orders) {
                output.append(order.getID().toString()).append("\n"); // This needs to get fixed later
            }
        } else return "";
        return output.toString();
    }

    @Override
    public abstract String toString();
}
