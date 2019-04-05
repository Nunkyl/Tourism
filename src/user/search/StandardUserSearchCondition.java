package user.search;

/**
 * Created by EL on 24.03.19.
 */
public class StandardUserSearchCondition extends BaseUserSearchCondition{

    private String passportID;

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public String getPassportID() {
        return passportID;
    }

    public boolean searchByPassportID(){
        return passportID != null;
    }
}
