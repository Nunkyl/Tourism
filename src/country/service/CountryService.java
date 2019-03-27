package country.service;

import common.solutions.service.BaseService;
import country.domain.BaseCountry;
import country.search.CountrySearchCondition;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public interface CountryService extends BaseService<BaseCountry, Integer> {

    List<? extends BaseCountry> search(CountrySearchCondition searchCondition);
}
