package country.service;

import common.business.service.BaseService;
import country.domain.Country;
import country.search.CountrySearchCondition;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public interface CountryService extends BaseService {

    void add(Country country);

    Country findByID(Integer id);

    void delete(Country country);

    List<Country> search(CountrySearchCondition searchCondition);
}
