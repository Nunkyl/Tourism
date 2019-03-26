package country.service;

import city.repo.implementation.CityMemoryListRepo;
import city.service.implementation.CityDefaultService;
import common.business.application.StorageType;
import country.repo.implementation.CountryMemoryListRepo;
import country.service.implementation.CountryDefaultService;

/**
 * Created by eliza on 27.02.19.
 */
public final class CountryServiceCreator {

    private CountryServiceCreator(){

    }

    public static CountryService getMarkService(StorageType storageType) {
        switch (storageType) {

            case LINKED_LIST:
                return new CountryDefaultService(new CountryMemoryListRepo(),
                        new CityDefaultService(new CityMemoryListRepo()));

            case DATABASE:{
                return null;
            }

            default: {
                return null;
            }
        }
    }
}
