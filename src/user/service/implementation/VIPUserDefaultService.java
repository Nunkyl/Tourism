package user.service.implementation;

import user.repo.UserRepo;

/**
 * Created by eliza on 06.03.19.
 */
public class VIPUserDefaultService extends BaseUserDefaultService {

    public VIPUserDefaultService(UserRepo userRepo) {

        super(userRepo);
    }
}
