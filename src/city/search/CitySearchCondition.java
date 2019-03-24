package city.search;

import common.business.search.BaseSearchCondition;

/**
 * Created by eliza on 27.02.19.
 */
public class CitySearchCondition extends BaseSearchCondition {

    private String name;
    private Integer population;
    //private boolean isCapital;
    //private BaseCountry country;
    //private Order order;


    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }
}
