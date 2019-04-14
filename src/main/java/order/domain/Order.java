package order.domain;

import city.domain.City;
import common.business.domain.BaseDomain;
import country.domain.BaseCountry;
import user.domain.BaseUser;

import java.util.List;

/**
 * Created by eliza on 22.02.19.
 */
public class Order extends BaseDomain {

    private float price;
    private BaseUser user;
    private List<BaseCountry> countries;
    private List<City> cities;

    private Integer userID;
    private Integer countryID;
    private Integer cityID;

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

    public void setCountries(List<BaseCountry> country) {
        this.countries = country;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public float getPrice() {
        return price;
    }

    public BaseUser getUser() {
        return user;
    }

    public List<BaseCountry> getCountries() {
        return countries;
    }

    public List<City> getCities() {
        return cities;
    }

    public Integer getUserID() {
        return userID;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public Integer getCityID() {
        return cityID;
    }

    private String citiesToString(){
        StringBuilder output = new StringBuilder();
        for (City city : cities) {
            output.append(city.getName()).append(" ");
        }
        return output.toString();
    }

    private String countriesToString(){
        StringBuilder output = new StringBuilder();
        for (BaseCountry country : countries) {
            output.append(country.getName()).append(" ");
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + ID +
                ", price=" + price +
                ", user=" + user.getFirstName() + " " + user.getLastName() +
                ", countries=" + countriesToString() +
                ", cities=" + citiesToString() +
                '}';
    }
}
