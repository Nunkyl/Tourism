package storage.initiator.inputdataparsers;

import city.domain.City;
import common.solutions.parser.FileParser;
import common.solutions.xml.stax.parse.CustomStaxReader;
import common.solutions.xml.stax.XMLStaxUtils.*;
import country.domain.BaseCountry;
import country.domain.ClimateCategory;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static common.solutions.xml.stax.XMLStaxUtils.readContent;

/**
 * Created by eliza on 27.03.19.
 */
public class CountriesAndCitiesStaxParser implements FileParser<List<BaseCountry>> {

    private final RuntimeException NO_END_TAG_FOUND_EXCEPTION = new RuntimeException("Suitable end tag NOT found");

    @Override
    public List<BaseCountry> parseFile(String file) throws Exception {

        List<BaseCountry> result = new LinkedList<>();

        try (CustomStaxReader staxReader = CustomStaxReader.newInstance(file)) {

            XMLStreamReader reader = staxReader.getReader();

            while (reader.hasNext()) { // Pull model
                int eventType = reader.next();

                switch (eventType) {
                    case XMLStreamReader.START_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("countries".equals(tagName)) {
                            result = new ArrayList<>(readDocument(reader));
                        }
                        break;
                    }

                    case XMLStreamConstants.END_ELEMENT: {
                        return result;
                    }

                    case 4:
                }
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private List<BaseCountry> readDocument(XMLStreamReader reader) throws XMLStreamException {

        List<BaseCountry> countries = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("country".equals(tagName)) {
                        countries.add(readCountry(reader));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return countries;
                }
                case 4:
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private BaseCountry readCountry(XMLStreamReader reader) throws XMLStreamException {

        String type = reader.getAttributeValue(null, "climateCategory");
        ClimateCategory countryDiscriminator = ClimateCategory.valueOf(type);

        BaseCountry country = null;
        if (ClimateCategory.COLD.equals(countryDiscriminator)) {
            country = new CountryWithColdClimate();
            country.setDiscriminator(ClimateCategory.COLD);
        } else {
            country = new CountryWithHotClimate();
            country.setDiscriminator(ClimateCategory.HOT);
        }

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("name".equals(tagName)) {
                        country.setName(readContent(reader).trim());
                    } else if ("languages".equals(tagName)) {
                        country.setLanguages(readContent(reader).trim());
                    } else if ("cities".equals(tagName)) {
                        country.setCities(readCities(reader));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return country;
                }

                case 4:
            }
        }
        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private List<City> readCities(XMLStreamReader reader) throws XMLStreamException {

        List<City> cities = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("city".equals(tagName)) {
                        cities.add(readCity(reader));
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    return cities;
                }
                case 4:
            }
        }
        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private City readCity(XMLStreamReader reader) throws XMLStreamException {

        City city = new City();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {

                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("name".equals(tagName)) {
                        city.setName(readContent(reader).trim());
                    } else if ("population".equals(tagName)) {
                        city.setPopulation(Integer.parseInt(readContent(reader).trim()));
                    } else if ("isCapital".equals(tagName)) {
                        city.setCapital(Boolean.parseBoolean(readContent(reader).trim()));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return city;
                }

                case 4:
            }
        }
        throw NO_END_TAG_FOUND_EXCEPTION;
    }
}