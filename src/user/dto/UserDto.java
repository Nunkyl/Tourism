package user.dto;

import common.business.dto.BaseDto;
import user.domain.UserCategory;


/**
 * Created by eliza on 14.04.19.
 */
public class UserDto extends BaseDto<Integer> {

    protected String firstName;
    protected String lastName;
    protected UserCategory discriminator;
    protected Integer orderID;

    public UserDto() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDiscriminator(UserCategory discriminator) {
        this.discriminator = discriminator;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
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

    public Integer getOrderID() {
        return orderID;
    }
}
