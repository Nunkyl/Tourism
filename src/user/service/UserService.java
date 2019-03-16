package user.service;

import common.solutions.service.BaseService;
import user.domain.BaseUser;
import user.search.BaseUserSearchCondition;

import java.util.List;


/**
 * Created by eliza on 22.02.19.
 */
public interface UserService extends BaseService<BaseUser, Integer> {

    List<BaseUser> search(BaseUserSearchCondition searchCondition);
}
