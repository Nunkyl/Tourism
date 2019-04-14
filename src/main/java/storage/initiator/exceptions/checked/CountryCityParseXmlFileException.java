package storage.initiator.exceptions.checked;

import common.business.exception.TourismCheckedExceptions;

/**
 * Created by eliza on 14.04.19.
 */
public class CountryCityParseXmlFileException extends TourismCheckedExceptions {

    public CountryCityParseXmlFileException(int code, String message, Exception cause) {
        super(code, message);
        initCause(cause);
    }
}
