package country.search;

/**
 * Created by eliza on 26.03.19.
 */
public class CountryWithHotClimateSearchCondition extends CountrySearchCondition {

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

    public boolean searchByHottestMonth(){
        return hottestMonth != null;
    }

    public boolean searchByAverageTemperature(){
        return averageTemperature != null;
    }
}
