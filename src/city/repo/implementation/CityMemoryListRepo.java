package city.repo.implementation;

import city.domain.City;
import city.repo.CityRepo;
import city.search.CitySearchCondition;
import order.domain.Order;

import java.util.LinkedList;
import java.util.List;

import static common.solutions.utils.StringUtils.isNotBlank;
import static storage.Storage.citiesInStorage;
import static storage.Storage.ordersInStorage;


/**
 * Created by eliza on 27.02.19.
 */
public class CityMemoryListRepo implements CityRepo{

    @Override
    public void add(City city) {
        citiesInStorage.add(city);
    }

    @Override
    public City findByID(Integer id) {
        Integer cityIndex = findCityIndexById(id);
        if (cityIndex != null) {
            return citiesInStorage.get(cityIndex);
        }
        return null;
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {

        List<City> answer = new LinkedList<>();

        if (searchCondition.getID() != null) {
            answer.add(this.findByID(searchCondition.getID()));
            return answer;
        } else {
            boolean searchByName = isNotBlank(searchCondition.getName());
            boolean searchByPopulation = searchCondition.getPopulation() != null;

            for (City city : citiesInStorage) {
                if (city != null) {
                    boolean found = false;

                    if (searchByName) {
                        found = searchCondition.getName().equals(city.getName());
                    }

                    if (searchByPopulation) {
                        found = found && searchCondition.getPopulation().equals(city.getPopulation());
                    }

                    if (found) {
                        answer.add(city);
                    }
                }
            }
        }
        return answer; // Check this
    }

    @Override
    public void deleteByID(Integer id) {
        Integer modelIndex = findCityIndexById(id);

        if (modelIndex != null) {
            deleteCityByIndex(modelIndex);
        }
    }

    private void deleteCityByIndex(int index) {
        citiesInStorage.remove(index);
    }

    @Override
    public void printAll() {
        for (City city : citiesInStorage) {
            if (city != null) {
                System.out.println(city);
            }
        }
    }

    private Integer findCityIndexById(Integer modelId) {
        int index = 0;
        for (City city: citiesInStorage) {
            if (modelId.equals(city.getID()))
                return index;
            else index++;
        }
        return null;
    }

    @Override
    public void update(City city) {
        // Implement later
    }
}
