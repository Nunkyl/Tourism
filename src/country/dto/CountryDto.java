package country.dto;

import city.domain.City;
import city.dto.CityDto;
import common.business.dto.BaseDto;
import country.domain.ClimateCategory;
import country.domain.ClimateType;
import order.domain.Order;

import java.util.List;

/**
 * Created by eliza on 14.04.19.
 */
public class CountryDto extends BaseDto<Integer> {

    protected String name;
    protected String languages;
    protected ClimateCategory discriminator;
    protected ClimateType climateType;

    private List<CityDto> cities;

    public CountryDto() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguages(String language) {
        this.languages = language;
    }

    public void setCities(List<CityDto> cities) {
        this.cities = cities;
    }

    public void setDiscriminator(ClimateCategory discriminator) {
        this.discriminator = discriminator;
    }

    public void setClimateType(ClimateType climateType) {
        this.climateType = climateType;
    }

    public ClimateCategory getDiscriminator() {
        return discriminator;
    }

    public String getName() {
        return name;
    }

    public String getLanguages() {
        return languages;
    }

    public List<CityDto> getCities() {
        return cities;
    }

    public ClimateType getClimateType() {
        return climateType;
    }

}
