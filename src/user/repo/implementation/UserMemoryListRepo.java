package user.repo.implementation;

import user.domain.BaseUser;
import user.repo.UserRepo;
import user.search.BaseUserSearchCondition;
import user.search.VIPUserSearchCondition;

import static common.solutions.utils.StringUtils.isNotBlank;
import static storage.Storage.usersInStorage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public class UserMemoryListRepo implements UserRepo {

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
    public void addUser(BaseUser user) {
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
    }

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
}
