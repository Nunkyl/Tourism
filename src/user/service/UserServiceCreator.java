package user.service;

import common.business.application.StorageType;
import user.repo.implementation.UserMemoryListRepo;
import user.service.implementation.UserDefaultService;

/**
 * Created by eliza on 26.02.19.
 */
public final class UserServiceCreator {

    private UserServiceCreator() {

    }

    public static UserService getUserService(StorageType storageType) {

        switch(storageType){
            case LINKED_LIST:
                return new UserDefaultService(new UserMemoryListRepo());
            case DATABASE:
                return null;
            default:
                return null;
        }
    }
}
