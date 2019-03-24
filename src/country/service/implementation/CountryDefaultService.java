package country.service.implementation;

import city.domain.City;
import city.repo.CityRepo;
import country.domain.BaseCountry;
import country.repo.CountryRepo;
import country.search.CountrySearchCondition;
import country.service.CountryService;
import storage.SimpleSequenceGenerator;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public class CountryDefaultService implements CountryService{

    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;
    /*
    @Override
    public void setSequenceGenerator(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }
    */

    public CountryDefaultService(CountryRepo countryRepo, CityRepo cityRepo) {
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
    }

    @Override
    public void add(BaseCountry country) {
        if (country != null) {
            country.setID(SimpleSequenceGenerator.getNextID());
            countryRepo.add(country);

            if (country.getCities() != null) {
                for (City city : country.getCities()) {
                    if (city != null) {
                        cityRepo.add(city);
                    }
                }
            }
        }
    }

    @Override
    public BaseCountry findByID(Integer id) {
        if (id != null) {
            return countryRepo.findByID(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(BaseCountry country) {
        if (country.getID() != null) {
            this.deleteByID(country.getID());
        }
    }

    @Override
    public void deleteByID(Integer id) {
        if (id != null) {
            countryRepo.deleteByID(id);
        }
    }

    @Override
    public void printAll() {
        countryRepo.printAll();
    }


    @Override
    public List<BaseCountry> search(CountrySearchCondition searchCondition) {
        return countryRepo.search(searchCondition);
    }

    @Override
    public void update(BaseCountry country) {
        countryRepo.update(country);
    }
}
