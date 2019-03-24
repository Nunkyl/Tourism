package city.service.implementation;

import city.domain.City;
import city.repo.CityRepo;
import city.search.CitySearchCondition;
import city.service.CityService;
import storage.SimpleSequenceGenerator;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public class CityDefaultService implements CityService {

    private final CityRepo cityRepo;
    private SimpleSequenceGenerator sequenceGenerator;

    /*
    @Override
    public void setSequenceGenerator(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }
    */

    public CityDefaultService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    @Override
    public void add(City city) {
        if (city != null) {
            city.setID(SimpleSequenceGenerator.getNextID());
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

    @Override
    public void update(City city) {
        cityRepo.update(city);
    }
}
