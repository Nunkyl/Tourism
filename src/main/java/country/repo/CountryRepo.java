package country.repo;

import common.solutions.repo.BaseRepo;
import country.domain.BaseCountry;
import country.search.CountrySearchCondition;

import java.util.List;

/**
 * Created by eliza on 22.02.19.
 */
public interface CountryRepo extends BaseRepo<BaseCountry, Integer> {

    List<? extends BaseCountry> search(CountrySearchCondition searchCondition);
}
