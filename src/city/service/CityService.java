package city.service;

import city.domain.City;
import city.search.CitySearchCondition;
import common.solutions.service.BaseService;

import java.util.List;

/**
 * Created by eliza on 27.02.19.
 */
public interface CityService extends BaseService<City, Integer> {

    List<City> search(CitySearchCondition searchCondition);
}
