package common.business.application.servicefactory;

import common.business.application.StorageType;
import city.service.CityService;
import country.service.CountryService;
import order.service.OrderService;
import user.service.UserService;

/**
 * Created by eliza on 27.02.19.
 */
public final class ServiceSupplier {

    private static volatile ServiceSupplier INSTANCE;
    private ServiceFactory serviceFactory;

    private ServiceSupplier(StorageType storageType) {
        initServiceFactory(storageType);
    }

    public static ServiceSupplier getInstance() {
        return INSTANCE;
    }

    public static ServiceSupplier newInstance(StorageType storageType) {

        if (INSTANCE == null) {
            synchronized (ServiceSupplier.class) { // ????????
                if (INSTANCE == null) {
                    INSTANCE = new ServiceSupplier(storageType);
                }
            }
        }
        return INSTANCE;
    }

    private void initServiceFactory(StorageType storageType) {
        switch (storageType) {
            case LINKED_LIST: {
                serviceFactory = new MemoryListServiceFactory();
            }
            default: {
                serviceFactory = new MemoryListServiceFactory();
            }
        }
    }

    public CountryService getCountryService() {
        return serviceFactory.getCountryService();
    }

    public CityService getCityService() {
        return serviceFactory.getCityService();
    }

    public OrderService getOrderService() {
        return serviceFactory.getOrderService();
    }

    public UserService getUserService() {
        return serviceFactory.getUserService();
    }
}
