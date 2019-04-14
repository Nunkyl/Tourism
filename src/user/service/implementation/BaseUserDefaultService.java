package user.service.implementation;


import order.domain.Order;
import user.domain.BaseUser;
import user.repo.UserRepo;
import user.search.BaseUserSearchCondition;
import user.service.UserService;
import storage.SimpleSequenceGenerator;

import java.util.Collection;
import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public class BaseUserDefaultService implements UserService {

    private final UserRepo userRepo;

    public BaseUserDefaultService(UserRepo userRepo) {

        this.userRepo = userRepo;
    }

    @Override
    public BaseUser add(BaseUser user) {
        if (user != null) {
            user.setID(SimpleSequenceGenerator.getNextID());
            userRepo.add(user);
        }
        return user;
    }

    @Override
    public void add(Collection<BaseUser> users) {
        if (users != null) {
            for (BaseUser user: users) {
                user.setID(SimpleSequenceGenerator.getNextID());
            }
            userRepo.add(users);
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
    public List<? extends BaseUser> search(BaseUserSearchCondition searchCondition) {
        return userRepo.search(searchCondition);
    }

    @Override
    public void update(BaseUser user) {
        userRepo.update(user);
    }

    @Override
    public List<BaseUser> findAll() {
        return userRepo.findAll();
    }
}
