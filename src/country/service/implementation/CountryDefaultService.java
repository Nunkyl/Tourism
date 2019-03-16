package country.service.implementation;

import city.domain.City;
import city.repo.CityRepo;
import common.solutions.sequencegenerator.SequenceGenerator;
import country.domain.Country;
import country.repo.CountryRepo;
import country.search.CountrySearchCondition;
import country.service.CountryService;
import common.solutions.sequencegenerator.implementation.SimpleSequenceGenerator;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public class CountryDefaultService implements CountryService{

    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;
    private SequenceGenerator sequenceGenerator;
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
    public void add(Country country) {
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
    public Country findByID(Integer id) {
        if (id != null) {
            return countryRepo.findByID(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Country country) {
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
    public List<Country> search(CountrySearchCondition searchCondition) {
        return countryRepo.search(searchCondition);
    }

    @Override
    public void update(Country country) {
        countryRepo.update(country);
    }
}
