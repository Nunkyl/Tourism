package user.repo;

import common.business.repo.BaseRepo;
import user.domain.BaseUser;
import user.search.BaseUserSearchCondition;

import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public interface UserRepo extends BaseRepo{

    void addUser(BaseUser user);

    BaseUser findByID(Integer ID);

    List<BaseUser> search(BaseUserSearchCondition searchCondition);
}
