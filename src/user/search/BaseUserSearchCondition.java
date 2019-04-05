package user.search;

import common.business.search.BaseSearchCondition;
import user.domain.UserCategory;

/**
 * Created by eliza on 26.02.19.
 */
public class BaseUserSearchCondition extends BaseSearchCondition{

    private String firstName;
    private String lastName;
    private UserCategory discriminator;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDiscriminator(UserCategory discriminator) {
        this.discriminator = discriminator;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserCategory getDiscriminator() {
        return discriminator;
    }

    public boolean searchByLastName(){
        return lastName != null && firstName == null;
    }

    public boolean searchByLastAndFirstName(){
        return lastName != null && firstName != null;
    }

    public boolean searchByDiscriminator(){
        return discriminator != UserCategory.NOT_SET;
    }
}
