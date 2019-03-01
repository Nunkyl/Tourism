package common.business.application.servicefactory;

import city.service.CityService;
import country.service.CountryService;
import order.service.OrderService;
import user.service.UserService;

/**
 * Created by eliza on 27.02.19.
 */
public interface ServiceFactory {

    CountryService getCountryService();
    CityService getCityService();
    OrderService getOrderService();
    UserService getUserService();

}
