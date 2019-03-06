package user.service.implementation;

import user.repo.UserRepo;

/**
 * Created by eliza on 06.03.19.
 */
public class StandardUserDefaultService extends BaseUserDefaultService {

    public StandardUserDefaultService(UserRepo userRepo) {

        super(userRepo);
    }
}
