package user.domain;

/**
 * Created by eliza on 25.02.19.
 */
public class ChildUser extends BaseUser {

    private BaseUser mainGuardian; // Not null
    private String birthCertificateInfo;
    private String foodRestrictions; // Should be an enum
    private boolean cribRequired;

    public ChildUser() {
    }

    public ChildUser(String firstName, String lastName, BaseUser mainGuardian) {
        super(firstName, lastName);
        this.mainGuardian = mainGuardian;
    }

    public void setMainGuardian(BaseUser mainGuardian) {
        this.mainGuardian = mainGuardian;
    }

    public void setBirthCertificateInfo(String birthCertificateInfo) {
        this.birthCertificateInfo = birthCertificateInfo;
    }

    public void setFoodRestrictions(String foodRestrictions) {
        this.foodRestrictions = foodRestrictions;
    }

    public void setCribRequired(boolean cribRequired) {
        this.cribRequired = cribRequired;
    }

    public BaseUser getMainGuardian() {
        return mainGuardian;
    }

    public String getBirthCertificateInfo() {
        return birthCertificateInfo;
    }

    public String getFoodRestrictions() {
        return foodRestrictions;
    }

    public boolean isCribRequired() {
        return cribRequired;
    }

    @Override
    public String toString() {
        return "ChildUser{" +
                "ID='" + ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Guardian='" + mainGuardian + '\'' +
                ", orders=" + ordersToString() +
                '}';
    }
}
