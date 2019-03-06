package user.service.implementation;

import user.repo.UserRepo;

/**
 * Created by eliza on 06.03.19.
 */
public class ChildUserDefaultService extends BaseUserDefaultService {

    public ChildUserDefaultService(UserRepo userRepo) {

        super(userRepo);
    }
}
