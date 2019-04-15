package country.domain;

import country.domain.BaseCountry;

/**
 * Created by eliza on 17.03.19.
 */
public class CountryWithColdClimate extends BaseCountry {

    public CountryWithColdClimate() {
        discriminator = ClimateCategory.COLD;
    }

    public CountryWithColdClimate(String name, String languages) {
        super(name, languages);
        discriminator = ClimateCategory.COLD;
    }

    private boolean polarNight;
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

    @Override
    public String toString() {
        return "BaseCountry{" +
                "ID=" + ID +
                ", climate=" + discriminator.name() + '\'' +
                ", name='" + name + '\'' +
                ", languages=" + languages +
                ", citiesInStorage=" + citiesToString() +
                ", ordersInStorage=" + ordersToString() +
                '}';
    }
}
