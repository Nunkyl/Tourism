package country.domain;

import country.domain.BaseCountry;

/**
 * Created by eliza on 17.03.19.
 */
public class CountryWithColdClimate extends BaseCountry {

    public CountryWithColdClimate() {
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

    public boolean isPolarNight() {
        return polarNight;
    }

    public Long getTelephoneCode() {
        return telephoneCode;
    }

    @Override
    public String toString() {
        return "BaseCountry{" +
                "ID=" + ID +
                ", type='" + discriminator.name() + '\'' +
                ", name='" + name + '\'' +
                ", languages=" + languages +
                ", citiesInStorage=" + citiesToString() +
                "ordersInStorage=" + ordersToString() +
                '}';
    }
}
