package user.domain;

/**
 * Created by eliza on 25.02.19.
 */
public class VIPUser extends BaseUser {

    private String passportID;
    private String nameOfPersonalAdmin;
    private String timeFrameForExtraFancyAds;
    private int bonus = 0;

    public VIPUser() {
    }

    public VIPUser(String firstName, String lastName, String passportID, String nameOfPersonalAdmin) {
        super(firstName, lastName);
        this.passportID = passportID;
        this.nameOfPersonalAdmin = nameOfPersonalAdmin;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setNameOfPersonalAdmin(String nameOfPersonalAdmin) {
        this.nameOfPersonalAdmin = nameOfPersonalAdmin;
    }

    public void setTimeFrameForExtraFancyAds(String timeFrameForExtraFancyAds) {
        this.timeFrameForExtraFancyAds = timeFrameForExtraFancyAds;
    }

    public String getPassportID() {
        return passportID;
    }

    public String getNameOfPersonalAdmin() {
        return nameOfPersonalAdmin;
    }

    public String getTimeFrameForExtraFancyAds() {
        return timeFrameForExtraFancyAds;
    }

    public int getBonus() {
        return bonus;
    }

    public void sendExtraFancyAds() {
        // Send an advert according to the chosen time frame of some luxury tour or rare location
    }

    @Override
    public String toString() {
        return "VIPUser{" +
                "ID='" + ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passportID + '\'' +
                ", nameOfPersonalAdmin='" + nameOfPersonalAdmin + '\'' +
                ", time frame for ads='" + timeFrameForExtraFancyAds + '\'' +
                ", ordersInStorage=" + ordersToString() +
                '}';
    }
}
