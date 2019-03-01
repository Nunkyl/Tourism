package order.domain;

import city.domain.City;
import common.business.domain.BaseDomain;
import country.domain.Country;
import user.domain.BaseUser;

import java.util.List;

/**
 * Created by eliza on 22.02.19.
 */
public class Order extends BaseDomain {

    private float price;
    private BaseUser user;
    private List<Country> countries = null;
    private List<City> cities = null;

    public Order() {
    }

    public Order(Integer ID, BaseUser user) {
        this.ID = ID;
        this.user = user;
    }

    public Order(Integer ID, float price, BaseUser user) {
        this.ID = ID;
        this.price = price;
        this.user = user;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setUser(BaseUser user) {
        this.user = user;
    }

    public void setCountries(List<Country> country) {
        this.countries = country;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public float getPrice() {
        return price;
    }

    public BaseUser getUser() {
        return user;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<City> getCities() {
        return cities;
    }

    private String citiesToString(){
        StringBuilder output = new StringBuilder();
        for (City city : cities) {
            output.append(city.getName()).append("\n");
        }
        return output.toString();
    }

    private String countriesToString(){
        StringBuilder output = new StringBuilder();
        for (Country country : countries) {
            output.append(country.getName()).append("\n");
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                ", ID=" + ID +
                ", price=" + price +
                ", user=" + user +
                ", countriesInStorage=" + countriesToString() +
                ", citiesInStorage=" + citiesToString() +
                '}';
    }
}
