package country.exception;

/**
 * Created by eliza on 06.04.19.
 */
public enum CountryExceptionInfo {

    DELETE_COUNTRY_REMAINING_ORDERS_ERROR(1, "Error while deleting country! " +
                            "There are still orders associated with this destination."),
    NO_COUNTRY_FOUND_ERROR(2, "Error while deleting country! " +
                            "No such country found in storage.");

    private int code;
    private String description;

    CountryExceptionInfo(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
