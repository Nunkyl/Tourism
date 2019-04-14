package user.domain;

/**
 * Created by eliza on 26.02.19.
 */
public class StandardUser extends BaseUser {

    private String passportID;
    private String timeFrameForRegularAds;

    public StandardUser(String firstName, String lastName, UserCategory discriminator, String passportID){
        super(firstName, lastName, discriminator);
        this.passportID = passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setTimeFrameForExtraFancyAds(String timeFrameForRegularAds) {
        this.timeFrameForRegularAds = timeFrameForRegularAds;
    }

    public String getPassportID() {
        return passportID;
    }

    public String getTimeFrameForExtraFancyAds() {
        return timeFrameForRegularAds;
    }

    public void sendRegularAds() {
        // Send an advert according to the chosen time frame
    }

    @Override
    public String toString() {
        return "StandardUser{" +
                "ID='" + ID + '\'' +
                ", discriminator='" + discriminator.name() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passportID + '\'' +
                ", time frame for ads='" + timeFrameForRegularAds + '\'' +
                ", ordersInStorage=" + ordersToString() +
                '}';
    }
}
