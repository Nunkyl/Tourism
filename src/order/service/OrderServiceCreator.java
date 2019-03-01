package order.service;

import common.business.application.StorageType;
import order.repo.implementation.OrderMemoryListRepo;
import order.service.implementation.OrderDefaultService;

/**
 * Created by eliza on 26.02.19.
 */
public final class OrderServiceCreator {

    private OrderServiceCreator(){

    }

    public static OrderService getOrderService(StorageType storageType) {
        switch (storageType) {

            case LINKED_LIST:
                return new OrderDefaultService(new OrderMemoryListRepo());

            case DATABASE:{
                return null;
            }

            default: {
                return null;
            }
        }
    }
}
