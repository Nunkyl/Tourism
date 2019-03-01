package user.service.implementation;


import user.domain.BaseUser;
import user.repo.UserRepo;
import user.search.BaseUserSearchCondition;
import user.service.UserService;

import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public class UserDefaultService implements UserService {

    private final UserRepo userRepo;

    public UserDefaultService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void add(BaseUser user) {
        if (user != null) {
            userRepo.addUser(user);
        }
    }

    @Override
    public BaseUser findByID(Integer id) {
        if (id != null) {
            return userRepo.findByID(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(BaseUser user) {
        if (user.getID() != null) {
            this.deleteByID(user.getID());
        }
    }

    @Override
    public void deleteByID(Integer id) {
        if (id != null) {
            userRepo.deleteByID(id);
        }
    }

    @Override
    public void printAll() {
        userRepo.printAll();
    }

    @Override
    public List<BaseUser> search(BaseUserSearchCondition searchCondition) {
        return userRepo.search(searchCondition);
    }

}
