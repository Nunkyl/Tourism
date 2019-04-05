package country.service.implementation;

import city.domain.City;
import city.repo.CityRepo;
import city.service.CityService;
import country.domain.BaseCountry;
import country.repo.CountryRepo;
import country.search.CountrySearchCondition;
import country.service.CountryService;
import storage.SimpleSequenceGenerator;

import java.util.Collection;
import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public class CountryDefaultService implements CountryService{

    private final CountryRepo countryRepo;
    private final CityService cityService;
    /*
    @Override
    public void setSequenceGenerator(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }
    */

    public CountryDefaultService(CountryRepo countryRepo, CityService cityService) {
        this.countryRepo = countryRepo;
        this.cityService = cityService;
    }

    @Override
    public void add(BaseCountry country) {
        if (country != null) {
            country.setID(SimpleSequenceGenerator.getNextID());
            countryRepo.add(country);

            if (country.getCities() != null) {
                for (City city : country.getCities()) {
                    if (city != null) {
                        cityService.add(city);
                    }
                }
            }
        }
    }

    @Override
    public void add(Collection<BaseCountry> countries) {
        if (countries != null) {
            for (BaseCountry country: countries) {
                country.setID(SimpleSequenceGenerator.getNextID());

                if (country.getCities() != null) {
                    for (City city : country.getCities()) {
                        if (city != null) {
                            cityService.add(city);
                        }
                    }
                }

            }
            countryRepo.add(countries);
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
    public List<? extends BaseCountry> search(CountrySearchCondition searchCondition) {
        return countryRepo.search(searchCondition);
    }

    @Override
    public void update(BaseCountry country) {
        countryRepo.update(country);
    }

    @Override
    public List<BaseCountry> findAll() {
        return countryRepo.findAll();
    }
}
