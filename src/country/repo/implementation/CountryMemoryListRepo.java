package country.repo.implementation;

import common.business.search.SortDirection;
import country.domain.BaseCountry;
import country.repo.CountryRepo;
import country.search.CountrySearchCondition;

import static common.solutions.utils.StringUtils.isNotBlank;
import static storage.Storage.countriesInStorage;

import java.util.*;

/**
 * Created by eliza on 27.02.19.
 */
public class CountryMemoryListRepo implements CountryRepo {

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
    public List<BaseCountry> search(CountrySearchCondition searchCondition) {


        /*
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

        */

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
