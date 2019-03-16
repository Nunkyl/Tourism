package country.service;

import common.solutions.service.BaseService;
import country.domain.Country;
import country.search.CountrySearchCondition;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public interface CountryService extends BaseService<Country, Integer> {

    List<Country> search(CountrySearchCondition searchCondition);
}
