package country.importcountries.implementation;

import city.domain.City;
import common.business.application.servicefactory.ServiceSupplier;
import country.domain.BaseCountry;
import country.domain.ClimateCategory;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
import country.importcountries.ImportCountriesInt;
import country.service.CountryService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by eliza on 17.03.19.
 */
public class ImportCountries implements ImportCountriesInt {

    public static CountryService addCountriesWithCitiesFromFile(String filePath){

        CountryService countryService = ServiceSupplier.getInstance().getCountryService();
        BaseCountry country;
        String[] attrs;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                if (line.equals("HOT")){
                    line = bufferedReader.readLine();
                    attrs = line.split("\\|");
                    country = new CountryWithHotClimate(attrs[0].trim(), attrs[1].trim());
                }
                else {
                    line = bufferedReader.readLine();
                    attrs = line.split("\\|");
                    country = new CountryWithColdClimate(attrs[0].trim(), attrs[1].trim());
                }

                country.setCities(new LinkedList<>());
                int numberOfCities = Integer.parseInt(attrs[2].trim());

                for (int i = 0; i < numberOfCities; i++){
                    line = bufferedReader.readLine();
                    attrs = (line).split("\\|");

                    City city = new City(attrs[0].trim(), Integer.parseInt(attrs[1].trim()),
                            Boolean.parseBoolean(attrs[2].trim()), country);

                    country.getCities().add(city);
                }

                countryService.add(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countryService;
    }

}













