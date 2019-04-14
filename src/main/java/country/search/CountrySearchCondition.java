package country.search;

import city.domain.City;
import common.business.search.BaseSearchCondition;
import country.domain.ClimateCategory;
import order.domain.Order;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public class CountrySearchCondition extends BaseSearchCondition <Integer> {

    private ClimateCategory discriminator;
    private String name;
    private String language;
    private CountrySortField sortField;

    public void setDiscriminator(ClimateCategory discriminator) {
        this.discriminator = discriminator;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String languages) {
        this.language = languages;
    }

    public void setSortField(CountrySortField sortField) {
        this.sortField = sortField;
    }

    public ClimateCategory getDiscriminator() {
        return discriminator;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public CountrySortField getSortField() {
        return sortField;
    }

    public boolean searchByName(){
        return name != null;
    }

    public boolean searchByLanguage(){
        return language != null;
    }

    public boolean searchByDiscriminator(){
        return discriminator != null;
    }
}
