package storage.initiator.inputdataparsers;

import city.domain.City;
import country.domain.BaseCountry;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
import common.solutions.parser.FileParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by eliza on 22.03.19.
 */
public class CountriesAndCitiesTextFileParser implements FileParser<List<BaseCountry>> {

    @Override
    public List<BaseCountry> parseFile(String filePath) throws Exception {

        List<BaseCountry> countriesFromFile = new LinkedList<>();
        BaseCountry country;
        String[] attrs;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                if (line.equals("HOT")) {
                    line = bufferedReader.readLine();
                    attrs = line.split("\\|");
                    country = new CountryWithHotClimate(attrs[0].trim(), attrs[1].trim());
                } else {
                    line = bufferedReader.readLine();
                    attrs = line.split("\\|");
                    country = new CountryWithColdClimate(attrs[0].trim(), attrs[1].trim());
                }

                country.setCities(new LinkedList<City>());
                int numberOfCities = Integer.parseInt(attrs[2].trim());

                for (int i = 0; i < numberOfCities; i++) {
                    line = bufferedReader.readLine();
                    attrs = (line).split("\\|");

                    City city = new City(attrs[0].trim(), Integer.parseInt(attrs[1].trim()),
                            Boolean.parseBoolean(attrs[2].trim()), country);

                    country.getCities().add(city);
                }

                countriesFromFile.add(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countriesFromFile;
    }
}