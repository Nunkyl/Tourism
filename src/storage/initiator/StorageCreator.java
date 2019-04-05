package storage.initiator;

import country.domain.BaseCountry;
import country.service.CountryService;
import storage.initiator.inputdataparsers.CountriesAndCitiesSaxParser;
import storage.initiator.inputdataparsers.CountriesAndCitiesTextFileParser;
import storage.initiator.inputdataparsers.FileParser;
import sun.jvm.hotspot.oops.Mark;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by eliza on 21.03.19.
 */
public class StorageCreator {

    private CountryService countryService;

    public StorageCreator(CountryService countryService) {
        this.countryService = countryService;
    }

    public enum DataSourceType {
        TXT_FILE,
        XML_FILE,
    }

    public enum ParserType {
        DOM,
        SAX,
        STAX
    }

    public void fillStorageWithCountriesAndCities(String[] filePath, DataSourceType[] dataSourceType, ParserType[] parserType)
            throws Exception {
        List<BaseCountry> countries = readCountriesFromFile(filePath, dataSourceType, parserType);

        if (!countries.isEmpty()) {
            for (BaseCountry country : countries) {
                countryService.add(country);
            }
        }
    }

    private List<BaseCountry> readCountriesFromFile(String[] filePath, DataSourceType[] dataSourceType, ParserType[] parserType)
            throws Exception {

        FileParser<List<BaseCountry>> dataSourceReader = null;
        List<BaseCountry> result = new LinkedList<>();

        for(int i = 0; i < filePath.length; i++) {
            switch (dataSourceType[i]) {

                case TXT_FILE: {
                    dataSourceReader = new CountriesAndCitiesTextFileParser();
                    break;
                }

                case XML_FILE: {

                    switch (parserType[i]) {
                        case DOM: {
                            //dataSourceReader = new CountriesWithCitiesXmlDomParser();
                            break;
                        }

                        case SAX: {
                            //result.addAll(new ThreadInitiator<>(filePath[i], new CountriesAndCitiesSaxParser()).
                                    //readDataFromFileInSeparateThread());
                            //dataSourceReader = new CountriesAndCitiesSaxParser();
                            break;
                        }

                        case STAX: {
                            //dataSourceReader = new CountriesWithCitiesXmlStaxParser();
                            break;
                        }
                    }
                    break;
                }
            }
        }

        //return dataSourceReader.parseFile(filePath);
        return result;
    }

}



