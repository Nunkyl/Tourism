package storage.initiator;

import country.domain.BaseCountry;
import storage.initiator.inputdataparsers.CountriesAndCitiesSaxParser;
import storage.initiator.inputdataparsers.CountriesAndCitiesTextFileParser;
import storage.initiator.inputdataparsers.FileParser;

import java.io.File;
import java.util.List;

/**
 * Created by eliza on 21.03.19.
 */
public class ParserCreator implements Runnable{

    private DataSourceType dataSourceType;
    private ParserType parserType;
    private List<BaseCountry> countries;
    private Thread thread;
    private File fileToParse;
    private volatile Exception parseException;

    public ParserCreator(File fileToParse, DataSourceType dataSourceType, ParserType parserType) {
        this.fileToParse = fileToParse;
        this.dataSourceType = dataSourceType;
        this.parserType = parserType;
        thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            countries = readCountriesFromFile(fileToParse.getAbsolutePath(), dataSourceType, parserType);
        } catch (Exception e) {
            System.out.println("Error while parsing file with countries!");
            parseException = e;
        }
    }


    private List<BaseCountry> readCountriesFromFile(String filePath, DataSourceType dataSourceType, ParserType parserType)
            throws Exception {

        FileParser<List<BaseCountry>> dataSourceReader = null;

            switch (dataSourceType) {

                case TXT_FILE: {
                    dataSourceReader = new CountriesAndCitiesTextFileParser();
                    break;
                }
                case XML_FILE: {
                    switch (parserType) {
                        case DOM: {
                            //dataSourceReader = new CountriesWithCitiesXmlDomParser();
                            break;
                        }
                        case SAX: {
                            dataSourceReader = new CountriesAndCitiesSaxParser();
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
        return dataSourceReader.parseFile(filePath);
    }

    public synchronized List<BaseCountry> getCountries() {
        return countries;
    }

    public void asyncParseCountries() {
        thread.start();
    }

    public void blockUntilJobIsFinished() throws InterruptedException {
        thread.join();
    }

    public Exception getParseException() {
        return parseException;
    }
}



