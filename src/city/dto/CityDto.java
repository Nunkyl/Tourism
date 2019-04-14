package city.dto;

import common.business.dto.BaseDto;

/**
 * Created by eliza on 14.04.19.
 */
public class CityDto extends BaseDto<Integer> {

    private String name;
    private Integer population;
    private boolean isCapital;

    private Integer countryID;
    private Integer orderID;

    public CityDto() {
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

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
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

    public Integer getCountryID() {
        return countryID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    @Override
    public String toString() {
        return "City{" +
                "ID=" + id + '\'' +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", isCapital=" + isCapital +
                ", country=" + countryID +
                ", order=" + orderID +
                '}';
    }
}
