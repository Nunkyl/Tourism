package common.business.application.servicefactory;

import city.repo.implementation.CityMemoryListRepo;
import city.service.CityService;
import city.service.implementation.CityDefaultService;
import country.repo.implementation.CountryMemoryListRepo;
import country.service.CountryService;
import country.service.implementation.CountryDefaultService;
import order.repo.implementation.OrderMemoryListRepo;
import order.service.OrderService;
import order.service.implementation.OrderDefaultService;
import user.repo.implementation.BaseUserMemoryListRepo;
import user.service.UserService;
import user.service.implementation.BaseUserDefaultService;

/**
 * Created by eliza on 27.02.19.
 */
public class MemoryListServiceFactory implements ServiceFactory {

    @Override
    public CountryService getCountryService() {
        return new CountryDefaultService(new CountryMemoryListRepo(), new CityDefaultService(new CityMemoryListRepo()));
    }

    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityMemoryListRepo());
    }

    @Override
    public OrderService getOrderService() {
        return new OrderDefaultService(new OrderMemoryListRepo());
    }

    @Override
    public UserService getUserService() {
        return new BaseUserDefaultService(new BaseUserMemoryListRepo());
    }
}
