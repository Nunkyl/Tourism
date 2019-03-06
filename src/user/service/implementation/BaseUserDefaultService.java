package user.service.implementation;


import common.solutions.utils.sequencegenerator.SequenceGenerator;
import user.domain.BaseUser;
import user.repo.UserRepo;
import user.search.BaseUserSearchCondition;
import user.service.UserService;

import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public class BaseUserDefaultService implements UserService {

    private final UserRepo userRepo;
    private SequenceGenerator sequenceGenerator;

    @Override
    public void setSequenceGenerator(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    public BaseUserDefaultService(UserRepo userRepo) {

        this.userRepo = userRepo;
    }

    @Override
    public void add(BaseUser user) {
        if (user != null) {
            user.setID(sequenceGenerator.getNextID());
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

    @Override
    public void update(BaseUser user) {
        userRepo.update(user);
    }
}
