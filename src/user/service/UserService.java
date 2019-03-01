package user.service;

import common.business.service.BaseService;
import user.domain.BaseUser;
import user.search.BaseUserSearchCondition;

import java.util.List;


/**
 * Created by eliza on 22.02.19.
 */
public interface UserService extends BaseService {

    void add(BaseUser user);

    BaseUser findByID(Integer ID);

    void delete(BaseUser user);

    List<BaseUser> search(BaseUserSearchCondition searchCondition);
}
