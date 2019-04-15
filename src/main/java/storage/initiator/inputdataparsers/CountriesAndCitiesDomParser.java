package storage.initiator.inputdataparsers;

import city.domain.City;
import common.solutions.parser.FileParser;
import country.domain.BaseCountry;

import country.domain.ClimateCategory;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static common.solutions.xml.dom.XMLDomUtils.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by eliza on 27.03.19.
 */
public class CountriesAndCitiesDomParser implements FileParser<List<BaseCountry>> {

    @Override
    public List<BaseCountry> parseFile(String file) throws Exception {

        Document doc = getDocument(file);
        Element root = getOnlyElement(doc, "countries");

        NodeList xmlCountries = root.getElementsByTagName("country");
        List<BaseCountry> result = new ArrayList<>();

        for (int i = 0; i < xmlCountries.getLength(); i++) {
            result.add(getCountryFromXmlElement(xmlCountries.item(i)));
        }
        return result;
    }

    private BaseCountry getCountryFromXmlElement(Node xmlCountry) throws Exception {

        Element countryElement = (Element) xmlCountry;
        ClimateCategory type = ClimateCategory.valueOf(countryElement.getAttribute("climateCategory"));

        BaseCountry country;
        if (ClimateCategory.HOT.equals(type)){
            country = new CountryWithHotClimate();
        } else {
            country = new CountryWithColdClimate();
        }

        country.setName(getOnlyElementTextContent(countryElement, "name").trim());
        country.setLanguages(getOnlyElementTextContent(countryElement, "languages").trim());

        NodeList cities = (countryElement).getElementsByTagName("city");
        if (cities.getLength() > 0) {
            country.setCities(new ArrayList<>());

            for (int i = 0; i < cities.getLength(); i++) {
                City city = getCityFromXmlElement((Element) cities.item(i), country);
                country.getCities().add(city);
            }
        }
        return country;
    }

    private City getCityFromXmlElement(Element cityXml, BaseCountry country) {

        City city = new City();
        city.setCountry(country);
        city.setName(getOnlyElementTextContent(cityXml, "name").trim());
        city.setPopulation(Integer.parseInt(getOnlyElementTextContent(cityXml, "population").trim()));
        city.setCapital(Boolean.parseBoolean(getOnlyElementTextContent(cityXml, "isCapital").trim()));
        return city;
    }
}
