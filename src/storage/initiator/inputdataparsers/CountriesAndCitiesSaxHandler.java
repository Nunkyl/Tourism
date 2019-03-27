package storage.initiator.inputdataparsers;

import city.domain.City;
import country.domain.BaseCountry;
import country.domain.ClimateCategory;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
// import jdk.internal.org.xml.sax.helpers.DefaultHandler; Not this one!!!
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static common.solutions.utils.CollectionUtils.getLast;

/**
 * Created by eliza on 22.03.19.
 */
public class CountriesAndCitiesSaxHandler extends DefaultHandler {


    private static final String INIT_DATA_PATH = "";//InputData/countriesWithCities.xml
    private static final String COUNTRIES_PATH = INIT_DATA_PATH + "countries";
    private static final String COUNTRY_PATH = COUNTRIES_PATH + "/country";
    private static final String COUNTRY_NAME_PATH = COUNTRY_PATH + "/name";
    private static final String COUNTRY_LANGUAGES_PATH = COUNTRY_PATH + "/languages";
    private static final String CITIES_PATH = COUNTRY_PATH + "/cities";
    private static final String CITY_PATH = CITIES_PATH + "/city";
    private static final String CITY_NAME_PATH = CITY_PATH + "/name";
    private static final String CITY_POPULATION_PATH = CITY_PATH + "/population";
    private static final String CITY_IS_CAPITAL_PATH = CITY_PATH + "/isCapital";


    private StringBuilder content = new StringBuilder();
    private List<BaseCountry> countries = Collections.emptyList();
    private List<City> cities = Collections.emptyList();

    private Deque<String> pathInXml = new ArrayDeque<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        content.setLength(0);
        pathInXml.add(qName);

        switch (stackAsStringPath()) {
            case COUNTRIES_PATH: {
                countries = new LinkedList<>();
                break;
            }

            case COUNTRY_PATH: {
                if  (attributes.getValue("climateCategory").equals(ClimateCategory.COLD.toString())) {
                    countries.add(new CountryWithColdClimate());
                    getLast(countries).setDiscriminator(ClimateCategory.COLD);
                } else {
                    countries.add(new CountryWithHotClimate());
                    getLast(countries).setDiscriminator(ClimateCategory.HOT);
                }
                break;
            }

            case CITIES_PATH: {
                cities = new LinkedList<>();
                getLast(countries).setCities(cities);
                break;
            }

            case CITY_PATH: {
                cities.add(new City());
                getLast(cities).setCountry(getLast(countries));
                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String dataAsStr = content.toString();

        switch (stackAsStringPath()) {
            case COUNTRY_NAME_PATH: {
                getLast(countries).setName(dataAsStr.trim());
                break;
            }

            case COUNTRY_LANGUAGES_PATH: {
                getLast(countries).setLanguages(dataAsStr.trim());
                break;
            }

            case CITY_NAME_PATH: {
                getLast(cities).setName(dataAsStr.trim());
                break;
            }

            case CITY_POPULATION_PATH: {
                getLast(cities).setPopulation(Integer.parseInt(dataAsStr.trim()));
                break;
            }

            case CITY_IS_CAPITAL_PATH: {
                getLast(cities).setCapital(Boolean.parseBoolean(dataAsStr.trim()));
                break;
            }

        }
        pathInXml.removeLast();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        content.append(value.replaceAll("\\n",""));
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
    }

    public List<BaseCountry> getCountries() {
        return countries;
    }


    private String stackAsStringPath() {
        StringBuilder fullPath = new StringBuilder();

        Iterator<String> iter = pathInXml.iterator();
        while (iter.hasNext()) {
            String tag = iter.next();
            fullPath.append(tag);

            if (iter.hasNext()) {
                fullPath.append("/");
            }
        }

        return fullPath.toString();
    }

}
