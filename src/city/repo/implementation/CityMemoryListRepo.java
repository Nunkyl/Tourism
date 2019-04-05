package city.repo.implementation;

import city.domain.City;
import city.repo.CityRepo;
import city.search.CitySearchCondition;
import common.business.search.Paginator;
import common.business.search.SortDirection;
import common.solutions.utils.CollectionUtils;
import country.domain.BaseCountry;
import order.domain.Order;

import java.util.*;

import static common.solutions.utils.StringUtils.isNotBlank;
import static storage.Storage.citiesInStorage;


/**
 * Created by eliza on 27.02.19.
 */
public class CityMemoryListRepo implements CityRepo{

    private CitySortingComponent orderingComponent = new CitySortingComponent();

    @Override
    public void add(City city) {
        citiesInStorage.add(city);
    }

    @Override
    public void add(Collection<City> cities) {
        citiesInStorage.addAll(cities);
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
    public List<? extends City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getID() != null) {
            return Collections.singletonList(findByID(searchCondition.getID())); // returns an immutable list containing only the specified object
        } else {
            List<? extends City> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needSorting();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }

            if (!result.isEmpty() && searchCondition.needPaginator()) {
                result = getPageableData(result, searchCondition.getPaginator());
            }
            return result;
        }
    }

    private List<? extends City> getPageableData(List<? extends City> models, Paginator paginator) {
        return CollectionUtils.getPageableData(models, paginator.getLimit(), paginator.getOffset());
    }

    private List<City> doSearch(CitySearchCondition searchCondition) {
        List<City> result = new LinkedList<>();
        for (City city : citiesInStorage) { // from Storage
            if (city != null) {
                boolean found = true;

                if (searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(city.getName());
                }

                if (found && searchCondition.searchByCountryName()) {
                    found = searchCondition.getCountryName().equals(city.getCountry().getName());
                }

                if (found && searchCondition.searchByPopulation()) {
                    found = searchCondition.getPopulation().equals(city.getPopulation());
                }

                if (found) {
                    result.add(city);
                }
            }
        }
        return result;
    }

    /*
    // Old version of search
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

        if (searchCondition.getSortType() == SortDirection.ASC)
            Collections.sort(answer, new compareCity());

        if (searchCondition.getSortType() == SortDirection.DECS)
            Collections.sort(answer, Collections.reverseOrder(new compareCity()));

        return answer; // Check this

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

    */

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
