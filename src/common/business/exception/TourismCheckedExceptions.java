package common.business.exception;

/**
 * Created by eliza on 06.04.19.
 */
public class TourismCheckedExceptions extends Exception {

    protected int code;

    public TourismCheckedExceptions(int code, String message) {
        super(message);
        this.code = code;
    }

    public TourismCheckedExceptions(int code, String message, Exception cause) {
        super(message);
        this.code = code;
        initCause(cause);
    }

}
