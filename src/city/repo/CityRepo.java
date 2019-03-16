package city.repo;

import city.domain.City;
import city.search.CitySearchCondition;
import common.solutions.repo.BaseRepo;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public interface CityRepo extends BaseRepo<City, Integer> {

    List<City> search(CitySearchCondition searchCondition);
}

