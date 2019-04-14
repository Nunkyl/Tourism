package country.search;

/**
 * Created by eliza on 26.03.19.
 */
public class CountryWithColdClimateSearchCondition extends CountrySearchCondition {

    private Boolean polarNight;
    private Long telephoneCode;

    public void setPolarNight(boolean polarNight) {
        this.polarNight = polarNight;
    }

    public void setTelephoneCode(Long telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public boolean getPolarNight() {
        return polarNight;
    }

    public Long getTelephoneCode() {
        return telephoneCode;
    }

    public boolean searchByPolarNight(){
        return polarNight != null;
    }

    public boolean searchByTelephoneCode(){
        return telephoneCode != null;
    }
}
