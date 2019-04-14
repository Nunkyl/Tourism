package common.business.application.servicefactory;

import city.repo.CityRepo;
import city.repo.implementation.CityMemoryListRepo;
import city.service.CityService;
import city.service.implementation.CityDefaultService;
import country.repo.CountryRepo;
import country.repo.implementation.CountryMemoryListRepo;
import country.service.CountryService;
import country.service.implementation.CountryDefaultService;
import order.repo.OrderRepo;
import order.repo.implementation.OrderMemoryListRepo;
import order.service.OrderService;
import order.service.implementation.OrderDefaultService;
import user.repo.UserRepo;
import user.repo.implementation.BaseUserMemoryListRepo;
import user.service.UserService;
import user.service.implementation.BaseUserDefaultService;

/**
 * Created by eliza on 27.02.19.
 */
public class MemoryListServiceFactory implements ServiceFactory {

    private OrderRepo orderRepo = new OrderMemoryListRepo();
    private CountryRepo countryRepo = new CountryMemoryListRepo();
    private CityRepo cityRepo = new CityMemoryListRepo();
    private UserRepo userRepo = new BaseUserMemoryListRepo();

    private CityService cityService = new CityDefaultService(cityRepo, orderRepo);
    private OrderService orderService = new OrderDefaultService(orderRepo);
    private UserService userService = new BaseUserDefaultService(userRepo);
    private CountryService countryService = new CountryDefaultService(countryRepo, cityService, orderRepo);

    @Override
    public CountryService getCountryService() {
        return countryService;
    }

    @Override
    public CityService getCityService() {
        return cityService;
    }

    @Override
    public OrderService getOrderService() {
        return orderService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }
}
