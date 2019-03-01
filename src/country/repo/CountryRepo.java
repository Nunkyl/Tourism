package country.repo;

import common.business.repo.BaseRepo;
import country.domain.Country;
import country.search.CountrySearchCondition;

import java.util.List;

/**
 * Created by eliza on 22.02.19.
 */
public interface CountryRepo extends BaseRepo{

    void addCountry(Country country);

    Country findByID(Integer ID);

    List<Country> search(CountrySearchCondition searchCondition);
}
