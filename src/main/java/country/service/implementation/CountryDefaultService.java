package country.service.implementation;

import city.domain.City;
import city.service.CityService;
import common.business.exception.TourismUncheckedException;
import country.domain.BaseCountry;
import country.exception.unchecked.DeleteCountryException;
import country.repo.CountryRepo;
import country.search.CountrySearchCondition;
import country.service.CountryService;
import order.repo.OrderRepo;
import storage.SimpleSequenceGenerator;

import java.util.Collection;
import java.util.List;

import static country.exception.CountryExceptionInfo.*;

/**
 * Created by eliza on 27.02.19.
 */
public class CountryDefaultService implements CountryService{

    private final CountryRepo countryRepo;
    private final CityService cityService;
    private final OrderRepo orderRepo;

    public CountryDefaultService(CountryRepo countryRepo, CityService cityService, OrderRepo orderRepo) {
        this.countryRepo = countryRepo;
        this.cityService = cityService;
        this.orderRepo = orderRepo;
    }

    @Override
    public BaseCountry add(BaseCountry country) {
        if (country != null) {
            country.setID(SimpleSequenceGenerator.getNextID());
            countryRepo.add(country);

            if (country.getCities() != null) {
                for (City city : country.getCities()) {
                    if (city != null) {
                        city.setCountryID(country.getID());
                        cityService.add(city);
                    }
                }
            }
        }

        return country;
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
    public BaseCountry findByID(Integer ID) {
        if (ID != null) {
            return countryRepo.findByID(ID);
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
    public void removeAllCitiesFromCountry(Integer countryID) throws TourismUncheckedException {

        BaseCountry country = findByID(countryID);
        if (country != null){
            for (City city : country.getCities()) {
                cityService.deleteByID(city.getID());
            }
        } else throw new DeleteCountryException(NO_COUNTRY_FOUND_ERROR);
    }

    @Override
    public void deleteByID(Integer ID) throws TourismUncheckedException {
        if (ID != null) {
            boolean noOrders = orderRepo.getNumberOfOrdersForCountry(ID) == 0;

            if (noOrders) {
                removeAllCitiesFromCountry(ID);
                countryRepo.deleteByID(ID);
            } else throw new DeleteCountryException(DELETE_COUNTRY_REMAINING_ORDERS_ERROR);

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
