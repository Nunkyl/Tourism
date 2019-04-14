package city.service.implementation;

import city.domain.City;
import city.exception.unchecked.DeleteCityException;
import city.repo.CityRepo;
import city.search.CitySearchCondition;
import city.service.CityService;
import common.business.exception.TourismUncheckedException;
import storage.SimpleSequenceGenerator;
import order.repo.OrderRepo;

import java.util.Collection;
import java.util.List;

import static city.exception.CityExceptionInfo.*;

/**
 * Created by eliza on 27.02.19.
 */
public class CityDefaultService implements CityService {

    private final CityRepo cityRepo;
    private final OrderRepo orderRepo;
    //private SimpleSequenceGenerator sequenceGenerator;

    public CityDefaultService(CityRepo cityRepo, OrderRepo orderRepo) {
        this.cityRepo = cityRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public City add(City city) {
        if (city != null) {
            city.setID(SimpleSequenceGenerator.getNextID());
            cityRepo.add(city);
        }
        return city;
    }

    @Override
    public void add(Collection<City> cities) {
        if (cities != null) {
            for (City city: cities) {
                city.setID(SimpleSequenceGenerator.getNextID());
            }
            cityRepo.add(cities);
        }
    }

    @Override
    public City findByID(Integer id) {
        if (id != null) {
            return cityRepo.findByID(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(City order) {
        if (order.getID() != null) {
            this.deleteByID(order.getID());
        }
    }

    @Override
    public void deleteByID(Integer ID) throws TourismUncheckedException{
        if (ID != null) {
            boolean noOrders = orderRepo.getNumberOfOrdersForCity(ID) == 0;
            if (noOrders){
                cityRepo.deleteByID(ID);
            } else throw new DeleteCityException(DELETE_CITY_REMAINING_ORDERS_ERROR);
        } else throw new DeleteCityException(NO_CITY_FOUND_ERROR);
    }

    @Override
    public void printAll() {
        cityRepo.printAll();
    }

    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        return cityRepo.search(searchCondition);
    }

    @Override
    public void update(City city) {
        cityRepo.update(city);
    }

    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }
}
