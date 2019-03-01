package country.search;

import city.domain.City;
import common.business.search.BaseSearchCondition;
import order.domain.Order;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public class CountrySearchCondition extends BaseSearchCondition {

    private String name;
    private String languages;
    //private City city;
    //private Order order;


    public void setName(String name) {
        this.name = name;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public String getLanguages() {
        return languages;
    }
}
