package user.search;

/**
 * Created by EL on 24.03.19.
 */
public class ChildUserSearchCondition extends BaseUserSearchCondition{

    private String mainGuardian;
    private String foodRestrictions;

    public void setMainGuardian(String mainGuardian) {
        this.mainGuardian = mainGuardian;
    }

    public void setFoodRestrictions(String foodRestrictions) {
        this.foodRestrictions = foodRestrictions;
    }

    public String getMainGuardian() {
        return mainGuardian;
    }

    public String getFoodRestrictions() {
        return foodRestrictions;
    }

    public boolean searchByMainGuardian(){
        return mainGuardian != null;
    }

    public boolean searchByFoodRestrictions(){
        return foodRestrictions != null;
    }
}
