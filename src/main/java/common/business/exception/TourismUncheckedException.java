package common.business.exception;

/**
 * Created by eliza on 06.04.19.
 */
public class TourismUncheckedException extends RuntimeException {

    protected int code;

    public TourismUncheckedException(int code, String message) {
        super(message);
        this.code = code;
    }

    public TourismUncheckedException(int code, String message, Exception cause) {
        super(message);
        this.code = code;
        initCause(cause);
    }
}
