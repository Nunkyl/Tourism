package city.service;

import city.repo.implementation.CityMemoryListRepo;
import city.service.implementation.CityDefaultService;
import common.business.application.StorageType;

/**
 * Created by eliza on 27.02.19.
 */
public final class CityServiceCreator {

    private CityServiceCreator(){

    }

    public static CityService getMarkService(StorageType storageType) {
        switch (storageType) {

            case LINKED_LIST:
                //return new CityDefaultService(new CityMemoryListRepo());

            case DATABASE:{
                return null;
            }

            default: {
                return null;
            }
        }
    }
}
