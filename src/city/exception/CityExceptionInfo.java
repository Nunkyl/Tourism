package city.exception;

/**
 * Created by eliza on 06.04.19.
 */
public enum CityExceptionInfo {

    DELETE_CITY_REMAINING_ORDERS_ERROR(1, "Error while deleting city! " +
                            "There are still orders associated with this destination."),
    NO_CITY_FOUND_ERROR(2, "Error while deleting city! " +
                            "No such city found in storage.");

    private int code;
    private String description;

    CityExceptionInfo(int code, String description) {
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
