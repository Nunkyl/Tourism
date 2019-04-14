package country.exception.unchecked;

import common.business.exception.TourismUncheckedException;
import country.exception.CountryExceptionInfo;

/**
 * Created by eliza on 06.04.19.
 */
public class DeleteCountryException extends TourismUncheckedException {

    public DeleteCountryException(int code, String message) {
        super(code, message);
    }

    public DeleteCountryException(CountryExceptionInfo exceptionInfo) {
        super(exceptionInfo.getCode(), exceptionInfo.getDescription());
    }
}
