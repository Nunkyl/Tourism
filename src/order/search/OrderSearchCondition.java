package order.search;

import city.domain.City;
import common.business.search.BaseSearchCondition;
import country.domain.BaseCountry;
import user.domain.BaseUser;

/**
 * Created by eliza on 26.02.19.
 */
public class OrderSearchCondition extends BaseSearchCondition {

    private BaseUser user;
    private BaseCountry country;
    private City city;

    public void setUser(BaseUser user) {
        this.user = user;
    }

    public void setCountry(BaseCountry country) {
        this.country = country;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public BaseUser getUser() {
        return user;
    }

    public BaseCountry getCountry() {
        return country;
    }

    public City getCity() {
        return city;
    }
}
