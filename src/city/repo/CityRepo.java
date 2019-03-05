package city.repo;

import city.domain.City;
import city.search.CitySearchCondition;
import common.business.repo.BaseRepo;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public interface CityRepo extends BaseRepo {

    void add(City city);

    City findByID(Integer ID);

    List<City> search(CitySearchCondition searchCondition);

    void update (City city);
}
