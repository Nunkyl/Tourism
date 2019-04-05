package city.search;

import common.business.search.BaseSearchCondition;

/**
 * Created by eliza on 27.02.19.
 */
public class CitySearchCondition extends BaseSearchCondition<Integer> {

    private String name;
    private Integer population;
    private Boolean isCapital;
    private String countryName;
    private CitySortField sortField;
    //private Order order;


    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public void setCapital(Boolean capital) {
        isCapital = capital;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setSortField(CitySortField sortField) {
        this.sortField = sortField;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }

    public Boolean isCapital() {
        return isCapital;
    }

    public String getCountryName() {
        return countryName;
    }

    public CitySortField getSortField() {
        return sortField;
    }

    public boolean searchByName(){
        return name != null;
    }

    public boolean searchByPopulation(){
        return population != null;
    }
    public boolean searchByCapital(){
        return isCapital != null;
    }
    public boolean searchByCountryName(){
        return countryName != null;
    }
}
