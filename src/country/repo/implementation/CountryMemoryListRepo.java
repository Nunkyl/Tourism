package country.repo.implementation;

import country.domain.Country;
import country.repo.CountryRepo;
import country.search.CountrySearchCondition;
import user.domain.BaseUser;

import static common.solutions.utils.StringUtils.isNotBlank;
import static storage.Storage.countriesInStorage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created by eliza on 27.02.19.
 */
public class CountryMemoryListRepo implements CountryRepo {

    @Override
    public void deleteByID(Integer ID){

        Iterator<Country> iterator = countriesInStorage.iterator();

        while (iterator.hasNext()) {
            Country buffer = iterator.next();
            if (ID.equals(buffer.getID())) {
                iterator.remove();
            }
        }

    }

    @Override
    public void printAll(){
        for (Country user: countriesInStorage){
            System.out.println(user);
        }
    }

    @Override
    public void addCountry(Country user) {
        countriesInStorage.add(user);
    }

    @Override
    public Country findByID(Integer ID) {
        Integer userIndex = findUserIndexById(ID);
        if (userIndex != null) {
            return countriesInStorage.get(userIndex);
        }
        return null;
    }

    private Integer findUserIndexById(Integer ID) {
        int index = 0;
        for (Country user: countriesInStorage) {
            if (ID.equals(user.getID()))
                return index;
            else index++;
        }
        return null;
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {

        List<Country> answer = new LinkedList<>();

        if (searchCondition.getID() != null) {
            answer.add(this.findByID(searchCondition.getID()));
            return answer;
        } else {
            boolean searchByName = isNotBlank(searchCondition.getName());
            boolean searchByLanguages = isNotBlank(searchCondition.getLanguages());

            for (Country country : countriesInStorage) {
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
        return answer; // Check this
    }


    private void deleteUserByIndex(int index) {
        countriesInStorage.remove(index);
    }

}
