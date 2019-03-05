package country.service.implementation;

import city.domain.City;
import city.repo.CityRepo;
import country.domain.Country;
import country.repo.CountryRepo;
import country.search.CountrySearchCondition;
import country.service.CountryService;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public class CountryDefaultService implements CountryService{

    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;

    public CountryDefaultService(CountryRepo countryRepo, CityRepo cityRepo) {
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
    }

    @Override
    public void add(Country country) {
        if (country != null) {
            //country.setID();
            countryRepo.addCountry(country);

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
