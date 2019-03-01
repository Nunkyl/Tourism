package user.search;

import common.business.search.BaseSearchCondition;

/**
 * Created by eliza on 26.02.19.
 */
public class BaseUserSearchCondition extends BaseSearchCondition{

    private String firstName;
    private String lastName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
