package storage.initiator;

import country.domain.BaseCountry;
import country.service.CountryService;
import storage.initiator.exceptions.checked.CountryCityParseXmlFileException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static storage.initiator.exceptions.LoadDataExceptionInfo.PARSE_COUNTRY_CITY_ERROR;

/**
 * Created by eliza on 27.03.19.
 */
public class ThreadInitiator {

    private final CountryService countryService;

    public ThreadInitiator(CountryService countryService) {
        this.countryService = countryService;
    }

    public void initStorageWithMarksAndModels(List<File> files, DataSourceType dataSourceType, ParserType parserType)
            throws Exception {
        List<ParserCreator> countryCityFileParsers = prepareAsyncParsers(files, dataSourceType, parserType);
        List<BaseCountry> countries = asyncParseFilesAndWaitForResult(countryCityFileParsers);
        countryService.add(countries);
    }

    private List<ParserCreator> prepareAsyncParsers(List<File> files, DataSourceType dataSourceType,
                                                    ParserType parserType) {
        List<ParserCreator> markModelFileParsers = new ArrayList<>();
        for (File file : files) {
            markModelFileParsers.add(new ParserCreator(file, dataSourceType, parserType));
        }
        return markModelFileParsers;
    }

    private List<BaseCountry> asyncParseFilesAndWaitForResult(List<ParserCreator> workers) throws Exception {
        for (ParserCreator worker : workers) {
            worker.asyncParseCountries();
        }

        List<BaseCountry> countries = new ArrayList<>();
        for (ParserCreator worker : workers) {
            worker.blockUntilJobIsFinished();
            if (worker.getParseException() != null) {
                throw new CountryCityParseXmlFileException(PARSE_COUNTRY_CITY_ERROR.getCode(),
                        PARSE_COUNTRY_CITY_ERROR.getDescription(), worker.getParseException());
            }
            countries.addAll(worker.getCountries());
        }

        return countries;
    }
}
