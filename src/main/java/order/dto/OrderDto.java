package order.dto;

import city.domain.City;
import common.business.dto.BaseDto;
import country.domain.BaseCountry;
import user.domain.BaseUser;

import java.util.List;

/**
 * Created by eliza on 14.04.19.
 */
public class OrderDto extends BaseDto<Integer> {

    private float price;

    private Integer userID;
    private Integer countryID;
    private Integer cityID;

    public OrderDto() {
    }

    public void setPrice(float price) {
        this.price = price;
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

    public Integer getUserID() {
        return userID;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public Integer getCityID() {
        return cityID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + id +
                ", price=" + price +
                ", user=" + userID+
                ", country=" + countryID +
                ", city=" + cityID +
                '}';
    }
}
