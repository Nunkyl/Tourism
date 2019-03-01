package city.service;

import city.domain.City;
import city.search.CitySearchCondition;
import common.business.service.BaseService;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public interface CityService extends BaseService{

    void add(City city);

    City findByID(Integer id);

    void delete(City city);

    List<City> search(CitySearchCondition searchCondition);
}
