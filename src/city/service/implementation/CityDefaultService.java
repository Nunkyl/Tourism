package city.service.implementation;

import city.domain.City;
import city.repo.CityRepo;
import city.search.CitySearchCondition;
import city.service.CityService;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public class CityDefaultService implements CityService {

    private final CityRepo cityRepo;

    public CityDefaultService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    @Override
    public void add(City city) {
        if (city != null) {
            cityRepo.add(city);
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
    public void deleteByID(Integer id) {
        if (id != null) {
            cityRepo.deleteByID(id);
        }
    }

    @Override
    public void printAll() {
        cityRepo.printAll();
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        return cityRepo.search(searchCondition);
    }

}
