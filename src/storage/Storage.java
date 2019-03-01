package storage;

import user.domain.BaseUser;
import city.domain.City;
import country.domain.Country;
import order.domain.Order;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by eliza on 23.02.19.
 */
public class Storage {

    private static final int CAPACITY = 3;
    public static List<City> citiesInStorage = new LinkedList<>();
    public static List<Country> countriesInStorage = new LinkedList<>();
    public static List<Order> ordersInStorage = new LinkedList<>();
    public static List<BaseUser> usersInStorage = new LinkedList<>();
}
