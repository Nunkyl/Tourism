package user.repo;

import common.solutions.repo.BaseRepo;
import user.domain.BaseUser;
import user.search.BaseUserSearchCondition;

import java.util.List;

/**
 * Created by eliza on 26.02.19.
 */
public interface UserRepo extends BaseRepo<BaseUser, Integer>{

    List<? extends BaseUser> search(BaseUserSearchCondition searchCondition);
}
