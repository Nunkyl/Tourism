package user.repo.implementation;

import common.business.search.Paginator;
import common.solutions.utils.CollectionUtils;
import user.domain.*;
import user.repo.UserRepo;
import user.search.BaseUserSearchCondition;
import user.search.ChildUserSearchCondition;
import user.search.StandardUserSearchCondition;
import user.search.VIPUserSearchCondition;

import static common.solutions.utils.StringUtils.isNotBlank;
import static storage.Storage.usersInStorage;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public class BaseUserMemoryListRepo implements UserRepo {

    @Override
    public void deleteByID(Integer ID){

        Iterator<BaseUser> iterator = usersInStorage.iterator();

        while (iterator.hasNext()) {
            BaseUser buffer = iterator.next();
            if (ID.equals(buffer.getID())) {
                iterator.remove();
            }
        }

    }

    @Override
    public void printAll(){
        for (BaseUser user: usersInStorage){
            System.out.println(user);
        }
    }

    @Override
    public void add(BaseUser user) {
        usersInStorage.add(user);
    }

    @Override
    public BaseUser findByID(Integer ID) {
        Integer userIndex = findUserIndexById(ID);
        if (userIndex != null) {
            return usersInStorage.get(userIndex);
        }
        return null;
    }

    @Override
    public void add(Collection<BaseUser> users) {
        usersInStorage.addAll(users);
    }

    private Integer findUserIndexById(Integer ID) {
        int index = 0;
        for (BaseUser user: usersInStorage) {
            if (ID.equals(user.getID()))
                return index;
            else index++;
        }
        return null;
    }

    @Override
    public List<? extends BaseUser> search(BaseUserSearchCondition searchCondition) {

        UserCategory discriminator = searchCondition.getDiscriminator();

        List<? extends BaseUser> result = usersInStorage; // ???

        // Search by ID?

        switch (discriminator) {
            case STANDARD: {
                result = searchStandardUsers((StandardUserSearchCondition) searchCondition);
                break;
            }
            case CHILD: {
                result = searchChildUsers((ChildUserSearchCondition) searchCondition);
                break;
            }
            case VIP: {
                result = searchVIPUsers((VIPUserSearchCondition) searchCondition);
                break;
            }
            case NOT_SET: {
                result = searchAllUsers(searchCondition);
            }
        }

        if (!result.isEmpty() && searchCondition.needPaginator()) {
            result = getPageableData(result, searchCondition.getPaginator());
        }

        return result;
    }

    private List<? extends BaseUser> getPageableData(List<? extends BaseUser> models, Paginator paginator) {
        return CollectionUtils.getPageableData(models, paginator.getLimit(), paginator.getOffset());
    }

    private List<BaseUser> searchAllUsers(BaseUserSearchCondition searchCondition) {

        List<BaseUser> result = new LinkedList<>();

        for (BaseUser user : usersInStorage) { // from Storage

            boolean found = true;
            if (searchCondition.searchByLastName()) {
                found = searchCondition.getLastName().equals(user.getLastName());
            }

            if (found && searchCondition.searchByLastAndFirstName()) {
                found = (user.getFirstName() + " " + user.getLastName()).equals(searchCondition.getFirstName() + " " +
                        searchCondition.getLastName());
            }

            if (searchCondition.searchByDiscriminator()) {
                found = searchCondition.getDiscriminator().equals(user.getDiscriminator());
            }

            if (found) {
                result.add(user);
            }

        }
        return result;
    }

    private List<StandardUser> searchStandardUsers(StandardUserSearchCondition searchCondition) {

        List<StandardUser> result = new LinkedList<>();

        for (BaseUser user : usersInStorage) { // from Storage

            if (UserCategory.STANDARD.equals(user.getDiscriminator())) {
                StandardUser standardUser = (StandardUser) user;

                boolean found = true;
                if (searchCondition.searchByPassportID()) {
                    found = searchCondition.getPassportID().equals(standardUser.getPassportID());
                }

                if (found) {
                    result.add(standardUser);
                }
            }
        }
        return result;
    }

    private List<ChildUser> searchChildUsers(ChildUserSearchCondition searchCondition) {

        List<ChildUser> result = new LinkedList<>();

        for (BaseUser user : usersInStorage) { // from Storage

            if (UserCategory.CHILD.equals(user.getDiscriminator())) {
                ChildUser childUser = (ChildUser) user;

                boolean found = true;
                if (searchCondition.searchByMainGuardian()) {
                    found = searchCondition.getMainGuardian().equals(childUser.getMainGuardian().getLastName() + " " +
                            childUser.getMainGuardian().getFirstName());
                }

                if (found && searchCondition.searchByFoodRestrictions()) {
                    found = childUser.getFoodRestrictions().contains(searchCondition.getFoodRestrictions());
                }

                if (found) {
                    result.add(childUser);
                }
            }
        }
        return result;
    }

    private List<VIPUser> searchVIPUsers(VIPUserSearchCondition searchCondition) {

        List<VIPUser> result = new LinkedList<>();

        for (BaseUser user : usersInStorage) { // from Storage

            if (UserCategory.VIP.equals(user.getDiscriminator())) {
                VIPUser vipUser = (VIPUser) user;

                boolean found = true;
                if (searchCondition.searchByPassportID()) {
                    found = searchCondition.getPassportID().equals(vipUser.getPassportID());
                }

                if (found && searchCondition.searchByNameOfPersonalAdmin()) {
                    found = searchCondition.getNameOfPersonalAdmin().equals(vipUser.getNameOfPersonalAdmin());
                }

                if (found && searchCondition.searchByBonus()) {
                    found = searchCondition.getBonus().equals(vipUser.getBonus());
                }

                if (found) {
                    result.add(vipUser);
                }
            }
        }
        return result;
    }


    /*
    @Override
    public List<BaseUser> search(BaseUserSearchCondition searchCondition) {

        List<BaseUser> answer = new LinkedList<>();

        if (searchCondition.getID() != null) {
            answer.add(this.findByID(searchCondition.getID()));
            return answer;
        } else {
            boolean searchByFirstName = isNotBlank(searchCondition.getFirstName());
            boolean searchByLastName = isNotBlank(searchCondition.getLastName());

            for (BaseUser user : usersInStorage) {
                if (user != null) {
                    boolean found = false;

                    if (searchByFirstName) {
                        found = searchCondition.getFirstName().equals(user.getFirstName());
                    }

                    if (searchByLastName) {
                        found = found && searchCondition.getLastName().equals(user.getLastName());
                    }

                    if (found) {
                        answer.add(user);
                    }
                }
            }
        }
        return answer; // Check this
        return null;
    }
    */

    // Would it be better to return BaseUser or VIPUser?
    public List<BaseUser> searchVIP(VIPUserSearchCondition searchCondition){
        List<BaseUser> answer = new LinkedList<>();

        // Implement this  later

        return answer;

    }


    private void deleteUserByIndex(int index) {
        usersInStorage.remove(index);
    }


    @Override
    public void update (BaseUser user){
        // Fill in later
    }

    @Override
    public List<BaseUser> findAll() {
        return usersInStorage;
    }
}
