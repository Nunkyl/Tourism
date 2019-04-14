package user.search;

import common.business.search.BaseSearchCondition;

/**
 * Created by eliza on 26.02.19.
 */
public class VIPUserSearchCondition extends BaseUserSearchCondition {

    private String passportID;
    private String nameOfPersonalAdmin;
    private Integer bonus;

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setNameOfPersonalAdmin(String nameOfPersonalAdmin) {
        this.nameOfPersonalAdmin = nameOfPersonalAdmin;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public String getPassportID() {
        return passportID;
    }

    public String getNameOfPersonalAdmin() {
        return nameOfPersonalAdmin;
    }

    public Integer getBonus() {
        return bonus;
    }

    public boolean searchByPassportID(){
        return passportID != null;
    }

    public boolean searchByNameOfPersonalAdmin(){
        return nameOfPersonalAdmin != null;
    }

    public boolean searchByBonus(){
        return bonus != null;
    }
}
