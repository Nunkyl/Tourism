package storage.initiator.inputdataparsers;


import common.solutions.xml.xmlSaxParser;
import country.domain.BaseCountry;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
/**
 * Created by eliza on 22.03.19.
 */
public class CountriesAndCitiesSaxParser implements FileParser<List<BaseCountry>> {


    @Override
    public List<BaseCountry> parseFile(String file) throws Exception {
        SAXParser saxParser = xmlSaxParser.getParser();

        CountriesAndCitiesSaxHandler saxHandler = new CountriesAndCitiesSaxHandler();
        saxParser.parse(new File(file), saxHandler);
        return saxHandler.getCountries();
    }
}


