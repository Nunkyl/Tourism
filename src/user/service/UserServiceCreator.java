package user.service;

import common.business.application.StorageType;
import user.repo.implementation.BaseUserMemoryListRepo;
import user.service.implementation.BaseUserDefaultService;

/**
 * Created by eliza on 26.02.19.
 */
public final class UserServiceCreator {

    private UserServiceCreator() {

    }

    public static UserService getUserService(StorageType storageType) {

        switch(storageType){
            case LINKED_LIST:
                return new BaseUserDefaultService(new BaseUserMemoryListRepo());
            case DATABASE:
                return null;
            default:
                return null;
        }
    }
}
