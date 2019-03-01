package user.search;

import common.business.search.BaseSearchCondition;

/**
 * Created by eliza on 26.02.19.
 */
public class VIPUserSearchCondition extends BaseSearchCondition {

    private String nameOfPersonalAdmin;

    public void setNameOfPersonalAdmin(String nameOfPersonalAdmin) {
        this.nameOfPersonalAdmin = nameOfPersonalAdmin;
    }

    public String getNameOfPersonalAdmin() {
        return nameOfPersonalAdmin;
    }


}
