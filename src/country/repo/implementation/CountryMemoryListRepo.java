package country.repo.implementation;

import common.business.search.Paginator;
import common.business.search.SortDirection;
import common.solutions.utils.CollectionUtils;
import country.domain.BaseCountry;
import country.domain.ClimateCategory;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
import country.repo.CountryRepo;
import country.search.CountrySearchCondition;
import country.search.CountryWithColdClimateSearchCondition;
import country.search.CountryWithHotClimateSearchCondition;

import static common.solutions.utils.CollectionUtils.getPageableData;
import static common.solutions.utils.StringUtils.isNotBlank;
import static storage.Storage.countriesInStorage;

import java.util.*;

/**
 * Created by eliza on 27.02.19.
 */
public class CountryMemoryListRepo implements CountryRepo {

    private CountrySortingComponent sortingComponent = new CountrySortingComponent();

    @Override
    public void deleteByID(Integer ID){

        Iterator<BaseCountry> iterator = countriesInStorage.iterator();

        while (iterator.hasNext()) {
            BaseCountry buffer = iterator.next();
            if (ID.equals(buffer.getID())) {
                iterator.remove();
            }
        }

    }

    @Override
    public void printAll(){
        for (BaseCountry user: countriesInStorage){
            System.out.println(user);
        }
    }

    @Override
    public void add(BaseCountry user) {
        countriesInStorage.add(user);
    }

    @Override
    public BaseCountry findByID(Integer ID) {
        Integer userIndex = findUserIndexById(ID);
        if (userIndex != null) {
            return countriesInStorage.get(userIndex);
        }
        return null;
    }

    private Integer findUserIndexById(Integer ID) {
        int index = 0;
        for (BaseCountry user: countriesInStorage) {
            if (ID.equals(user.getID()))
                return index;
            else index++;
        }
        return null;
    }

    @Override
    public List<? extends BaseCountry> search(CountrySearchCondition searchCondition) {

        ClimateCategory discriminator = searchCondition.getDiscriminator();

        List<? extends BaseCountry> result = countriesInStorage; // ???

        // Search by ID?

        switch (discriminator) {
            case HOT: {
                //result =  sortResults(searchHotCountries((CountryWithHotClimateSearchCondition) searchCondition));
                result = searchHotCountries((CountryWithHotClimateSearchCondition) searchCondition);
                break;
            }
            case COLD: {
                result = searchColdCountries((CountryWithColdClimateSearchCondition) searchCondition);
                break;
            }
            case NOT_SET: {
                result = searchAllCountries(searchCondition);
            }
        }

        if (!result.isEmpty() && searchCondition.needSorting()) {
            sortingComponent.applyOrdering(result, searchCondition);
        }

        if (!result.isEmpty() && searchCondition.needPaginator()) {
            result = getPageableData(result, searchCondition.getPaginator());
        }

        return result;
    }

    private List<? extends BaseCountry> getPageableData(List<? extends BaseCountry> models, Paginator paginator) {
        return CollectionUtils.getPageableData(models, paginator.getLimit(), paginator.getOffset());
    }

    private List<? extends BaseCountry> sortResults(List<? extends BaseCountry> result, CountrySearchCondition searchCondition) {

            if (!result.isEmpty() && searchCondition.needSorting()) {
                sortingComponent.applyOrdering(result, searchCondition);
            }
            return result;
    }

    private List<BaseCountry> searchAllCountries(CountrySearchCondition searchCondition) {

        List<BaseCountry> result = new LinkedList<>();

        for (BaseCountry country : countriesInStorage) { // from Storage

                boolean found = true;
                if (searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(country.getName());
                }

                if (found && searchCondition.searchByLanguage()) {
                    found = country.getLanguages().contains(searchCondition.getLanguage());
                }

                if (found) {
                    result.add(country);
                }

        }
        return result;
    }

    private List<CountryWithHotClimate> searchHotCountries(CountryWithHotClimateSearchCondition searchCondition) {

        List<CountryWithHotClimate> result = new LinkedList<>();

        for (BaseCountry country : countriesInStorage) { // from Storage

            if (ClimateCategory.HOT.equals(country.getDiscriminator())) {
                CountryWithHotClimate hotCountry = (CountryWithHotClimate) country;

                boolean found = true;
                if (searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(hotCountry.getName());
                }

                if (found && searchCondition.searchByLanguage()) {
                    found = hotCountry.getLanguages().contains(searchCondition.getLanguage());
                }

                if (found && searchCondition.searchByHottestMonth()) {
                    found = searchCondition.getHottestMonth().equals(hotCountry.getHottestMonth());
                }

                if (found && searchCondition.searchByAverageTemperature()) {
                    found = searchCondition.getAverageTemperature().equals(hotCountry.getAverageTemperature());
                }

                if (found) {
                    result.add(hotCountry);
                }
            }
        }
        return result;
    }

    private List<CountryWithColdClimate> searchColdCountries(CountryWithColdClimateSearchCondition searchCondition) {

        List<CountryWithColdClimate> result = new LinkedList<>();

        for (BaseCountry country : countriesInStorage) { // from Storage

            if (ClimateCategory.COLD.equals(country.getDiscriminator())) {
                CountryWithColdClimate coldCountry = (CountryWithColdClimate) country;

                boolean found = true;
                if (searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(coldCountry.getName());
                }

                if (found && searchCondition.searchByLanguage()) {
                    found = coldCountry.getLanguages().contains(searchCondition.getLanguage());
                }

                if (found && searchCondition.searchByPolarNight()) {
                    found = searchCondition.getPolarNight() == coldCountry.getPolarNight();
                }

                if (found && searchCondition.searchByTelephoneCode()) {
                    found = searchCondition.getTelephoneCode().equals(coldCountry.getTelephoneCode());
                }

                if (found) {
                    result.add(coldCountry);
                }
            }
        }
        return result;
    }

    /*
    @Override
    public List<BaseCountry> search(CountrySearchCondition searchCondition) {

        List<BaseCountry> answer = new LinkedList<>();

        if (searchCondition.getID() != null) {
            answer.add(this.findByID(searchCondition.getID()));
            return answer;
        } else {
            boolean searchByName = isNotBlank(searchCondition.getName());
            boolean searchByLanguages = isNotBlank(searchCondition.getLanguages());

            for (BaseCountry country : countriesInStorage) {
                if (country != null) {

                    boolean found = true;

                    if (searchByName) {
                        found = searchCondition.getName().equals(country.getName());
                    }

                    if (searchByLanguages) {
                        String[] searchedLanguages = searchCondition.getLanguages().split("\\,");
                        boolean buf = false;
                        for (int i = 0; i < searchedLanguages.length; i++) {
                            if (country.getLanguages().contains(searchedLanguages[i].trim()))
                                buf = true;
                        }
                        found = found && buf;
                    }

                    if (found) {
                        answer.add(country);
                    }
                }
            }
        }

        if (searchCondition.getSortType() == SortDirection.ASC)
            Collections.sort(answer, new compareCountry());

        if (searchCondition.getSortType() == SortDirection.DECS)
            Collections.sort(answer, Collections.reverseOrder(new compareCountry()));

        return answer; // Check this

        return null;
    }

    private class compareCountry implements Comparator<BaseCountry> {

        @Override
        public int compare(BaseCountry o1, BaseCountry o2) {

            if (o1.getID().compareTo(o2.getID()) == 0){

                if (o1.getName().compareTo(o2.getName()) == 0){

                    return o1.getLanguages().compareTo(o2.getLanguages());

                } else return o1.getName().compareTo(o2.getName());

            } else return o1.getID().compareTo(o2.getID());
        }
    }
    */


    private void deleteUserByIndex(int index) {
        countriesInStorage.remove(index);
    }

    @Override
    public void update(BaseCountry country) {
        // Fill in later
    }

    @Override
    public List<BaseCountry> findAll() {
        return countriesInStorage;
    }
}
