package city.exception.unchecked;

import city.exception.CityExceptionInfo;
import common.business.exception.TourismUncheckedException;

/**
 * Created by eliza on 06.04.19.
 */
public class DeleteCityException extends TourismUncheckedException {

    public DeleteCityException(int code, String message) {
        super(code, message);
    }

    public DeleteCityException(CityExceptionInfo exceptionInfo) {
        super(exceptionInfo.getCode(), exceptionInfo.getDescription());
    }
}
