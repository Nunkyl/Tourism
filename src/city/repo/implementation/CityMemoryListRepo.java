package city.repo.implementation;

import city.domain.City;
import city.repo.CityRepo;
import city.search.CitySearchCondition;
import common.business.search.SortDirection;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static common.solutions.utils.StringUtils.isNotBlank;
import static storage.Storage.citiesInStorage;


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

        /*
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

        if (searchCondition.getSortType() == SortDirection.ASC)
            Collections.sort(answer, new compareCity());

        if (searchCondition.getSortType() == SortDirection.DECS)
            Collections.sort(answer, Collections.reverseOrder(new compareCity()));

        return answer; // Check this
        */
        return null;
    }

    private class compareCity implements Comparator<City> {

        @Override
        public int compare(City o1, City o2) {

            //if (o1.getID().compareTo(o2.getID()) == 0){

            if (o1.getName().compareTo(o2.getName()) == 0){

                return o1.getCountry().getName().compareTo(o2.getCountry().getName());

            } else return o1.getName().compareTo(o2.getName());

            //} else return o1.getID().compareTo(o2.getID());
        }
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

    @Override
    public List<City> findAll() {
        return citiesInStorage;
    }
}
