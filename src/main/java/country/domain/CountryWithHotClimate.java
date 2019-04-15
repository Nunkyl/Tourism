package country.domain;

/**
 * Created by eliza on 17.03.19.
 */
public class CountryWithHotClimate extends BaseCountry {

    public CountryWithHotClimate() {
        discriminator = ClimateCategory.HOT;
    }

    public CountryWithHotClimate(String name, String languages) {
        super(name, languages);
        discriminator = ClimateCategory.HOT;
    }

    private String hottestMonth;
    private Integer averageTemperature;

    public void setHottestMonth(String hottestMonth) {
        this.hottestMonth = hottestMonth;
    }

    public void setAverageTemperature(Integer averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public String getHottestMonth() {
        return hottestMonth;
    }

    public Integer getAverageTemperature() {
        return averageTemperature;
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
